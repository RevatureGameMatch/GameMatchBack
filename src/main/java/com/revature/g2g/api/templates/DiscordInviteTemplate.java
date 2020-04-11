package com.revature.g2g.api.templates;

import java.util.Objects;

public class DiscordInviteTemplate {
	private long guildId;
	private String guildName;
	private long channelId;
	private String channelName;
	private long inviteId;
	private String inviteCode;
	private String urlApp;
	private String urlWeb;
	public DiscordInviteTemplate() {
		super();
	}
	public long getGuildId() {
		return guildId;
	}
	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}
	public String getGuildName() {
		return guildName;
	}
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
	public long getChannelId() {
		return channelId;
	}
	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public long getInviteId() {
		return inviteId;
	}
	public void setInviteId(long inviteId) {
		this.inviteId = inviteId;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public String getUrlApp() {
		return urlApp;
	}
	public void setUrlApp(String urlApp) {
		this.urlApp = urlApp;
	}
	public String getUrlWeb() {
		return urlWeb;
	}
	public void setUrlWeb(String urlWeb) {
		this.urlWeb = urlWeb;
	}
	@Override
	public int hashCode() {
		return Objects.hash(channelId, channelName, guildId, guildName, inviteCode, inviteId, urlApp, urlWeb);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DiscordInviteTemplate)) {
			return false;
		}
		DiscordInviteTemplate other = (DiscordInviteTemplate) obj;
		return channelId == other.channelId && Objects.equals(channelName, other.channelName)
				&& guildId == other.guildId && Objects.equals(guildName, other.guildName)
				&& Objects.equals(inviteCode, other.inviteCode) && inviteId == other.inviteId
				&& Objects.equals(urlApp, other.urlApp) && Objects.equals(urlWeb, other.urlWeb);
	}
	@Override
	public String toString() {
		return "DiscordInviteTemplate [guildId=" + guildId + ", guildName=" + guildName + ", channelId=" + channelId
				+ ", channelName=" + channelName + ", inviteId=" + inviteId + ", inviteCode=" + inviteCode + ", urlApp="
				+ urlApp + ", urlWeb=" + urlWeb + "]";
	}
}