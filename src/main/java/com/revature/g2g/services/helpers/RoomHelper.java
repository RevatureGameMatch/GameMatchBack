package com.revature.g2g.services.helpers;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.DiscordInvite;
import com.revature.g2g.models.DiscordInviteStatus;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.DiscordInviteHandler;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.jda.JDASingleton;
import com.revature.g2g.services.jda.helpers.GuildHelper;
import com.revature.g2g.services.jda.helpers.RoleHelper;
import com.revature.g2g.services.jda.helpers.TextChannelHelper;
import com.revature.g2g.services.jda.helpers.VoiceChannelHelper;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.InviteAction;

@Service
public class RoomHelper {
	private GameHandler gameHandler;
	private DiscordHelper discordHelper;
	private GuildHelper guildHelper;
	private RoomHandler roomHandler;
	private DiscordInviteHandler discordInviteHandler;
	@Autowired
	public RoomHelper(GameHandler gameHandler, DiscordHelper discordHelper, GuildHelper guildHelper, RoomHandler roomHandler,
			DiscordInviteHandler discordInviteHandler) {
		super();
		this.gameHandler = gameHandler;
		this.discordHelper = discordHelper;
		this.guildHelper = guildHelper;
		this.roomHandler = roomHandler;
		this.discordInviteHandler = discordInviteHandler;
	}
	public Room insert(String name, RoomPlayStyle style, Game game, int maxPlayers, int currentPlayers, String description) {
		Room room = new Room(name, currentPlayers, maxPlayers, description, style, game);
		this.roomHandler.insert(clean(room));
		return room;
	}
	public Room clean(Room room) {
		Room r = new Room();
		r = ammend(r, room);
		r.setCreated(new Date());
		Guild guild = guildHelper.getGuild();
		ChannelAction<VoiceChannel> voiceChannel = VoiceChannelHelper.insert(guild, r.getName());
		ChannelAction<TextChannel> textChannel = TextChannelHelper.insert(guild, r.getName());
		Role role = RoleHelper.insert(guild, r.getName());
		voiceChannel.addPermissionOverride(role, discordHelper.getRoleVoicePermissions(), discordHelper.getRoleVoiceBans());
		textChannel.addPermissionOverride(role, discordHelper.getRoleTextPermissions(), discordHelper.getRoleTextBans());
		r.setDiscordVoiceChannelId(voiceChannel.complete().getIdLong());
		r.setDiscordTextChannelId(textChannel.complete().getIdLong());
		r.setDiscordRoleId(role.getIdLong());
		return r;
	}
	public Room ammend(Room original, Room changes) {
		original.setCurrentPlayers(changes.getCurrentPlayers());
		original.setDescription(Jsoup.clean(changes.getDescription(),Whitelist.none()));
		if(changes.getGame() != null) {
			original.setGame(gameHandler.findById(changes.getGame().getGameId()));
		}
		original.setMaxPlayers(changes.getMaxPlayers());
		original.setName(Jsoup.clean(changes.getName(), Whitelist.none()));
		original.setStatus(RoomStatus.JOINING);
		if(changes.getStyle() instanceof RoomPlayStyle) {
			original.setStyle(changes.getStyle());
		}else {
			original.setStyle(RoomPlayStyle.CASUAL);
		}
		return original;
	}
	public String getInvite(Room room, Player player) {
		JDA jda = JDASingleton.getJda();
		TextChannel text = jda.getTextChannelById(room.getDiscordTextChannelId());
		InviteAction inviteAction = text.createInvite();
		Invite invite = inviteAction.complete();
		DiscordInvite discordInvite = new DiscordInvite();
		discordInvite.setDiscordCode(invite.getCode());
		discordInvite.setRoom(room);
		discordInvite.setPlayer(player);
		discordInvite.setStatus(DiscordInviteStatus.CREATED);
		discordInviteHandler.insert(discordInvite);
		return invite.getUrl();
	}
}
