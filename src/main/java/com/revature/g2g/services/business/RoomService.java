package com.revature.g2g.services.business;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.helpers.DiscordHelper;
import com.revature.g2g.services.jda.helpers.GuildHelper;
import com.revature.g2g.services.jda.helpers.RoleHelper;
import com.revature.g2g.services.jda.helpers.TextChannelHelper;
import com.revature.g2g.services.jda.helpers.VoiceChannelHelper;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

@Service
public class RoomService {
	private GuildHelper guildHelper;
	private DiscordHelper discordHelper;
	private RoomHandler roomHandler;
	@Autowired
	public RoomService(GuildHelper guildHelper, DiscordHelper discordHelper, RoomHandler roomHandler) {
		super();
		this.guildHelper = guildHelper;
		this.discordHelper = discordHelper;
		this.roomHandler = roomHandler;
	}
	public Room make(String name, RoomPlayStyle style, Game game) {
		Guild guild = guildHelper.getGuild();
		Room room = new Room();
		room.setName(name);
		room.setGame(game);
		room.setMaxPlayers(new Random().nextInt(6) + 3);
		room.setCurrentPlayers(new Random().nextInt(3));
		room.setCreated(new Date());
		room.setStatus(RoomStatus.JOINING);
		room.setStyle(style);
		ChannelAction<VoiceChannel> voiceChannel = VoiceChannelHelper.insert(guild, name);
		ChannelAction<TextChannel> textChannel = TextChannelHelper.insert(guild, name);
		Role role = RoleHelper.insert(guild, name);
		voiceChannel.addPermissionOverride(role, discordHelper.getRoleVoicePermissions(), discordHelper.getRoleVoiceBans());
		textChannel.addPermissionOverride(role, discordHelper.getRoleTextPermissions(), discordHelper.getRoleTextBans());
		room.setDiscordVoiceChannelId(voiceChannel.complete().getIdLong());
		room.setDiscordTextChannelId(textChannel.complete().getIdLong());
		room.setDiscordRoleId(role.getIdLong());
		this.roomHandler.insert(room);
		return room;
	}
}