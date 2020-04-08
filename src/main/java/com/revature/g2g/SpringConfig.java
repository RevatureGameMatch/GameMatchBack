package com.revature.g2g;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
//	@Autowired
//	private LoggerSingleton loggerSingleton;
//	@Autowired
//	private PropertiesHelper propertiesHelper;
//	@Autowired
//	private GuildVoiceEventListener guildVoiceEventListener;
//	@Autowired
//	private MessageListener messageListener;
//	@Autowired
//	private ReadyListener readyListener;
//	@Bean(name="jda")
//	public JDA getJDA() throws LoginException {
//		JDABuilder builder = JDABuilder.createDefault(propertiesHelper.getPropValues().getProperty("discordKey"));
//		builder.setActivity(Activity.watching("Users for new commands."));
//		builder.addEventListeners(guildVoiceEventListener);
//		builder.addEventListeners(messageListener);
//		builder.addEventListeners(readyListener);
//		JDA jda = builder.build();
//		loggerSingleton.getDiscordLog().trace("JDA building");
//		return jda;
//	}
}