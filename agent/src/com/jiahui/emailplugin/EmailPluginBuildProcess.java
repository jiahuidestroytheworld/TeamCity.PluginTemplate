/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jiahui.emailplugin;

/**
 * Created by Jiahui.Chen on 12/18/2014.
 */

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.log.Loggers;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailPluginBuildProcess implements BuildProcess {
  private final AgentRunningBuild myBuild;
  private final BuildRunnerContext myContext;
  private BuildProgressLogger logger;
  final String username = "server9484";
  final String password = "Xa6b3JBj29KmQ";


  public EmailPluginBuildProcess(@NotNull AgentRunningBuild build, @NotNull BuildRunnerContext context){
    myBuild = build;
    myContext = context;
  }

  public void start() throws RunBuildException {
    Map<String, String> runnerParams = new HashMap<String, String>(myContext.getRunnerParameters());
    Loggers.SERVER.info("start getting parameters");
    for(Map.Entry<String, String> entry : runnerParams.entrySet()){
      RunnerParamsProvider.setProperty(entry.getKey(), entry.getValue());
    }
    Loggers.SERVER.info("start extracting parameters");
    String title = RunnerParamsProvider.props.getProperty("emailTitle");
    String from = RunnerParamsProvider.props.getProperty("emailFrom");
    String to = RunnerParamsProvider.props.getProperty("emailTo");
    String body = RunnerParamsProvider.props.getProperty("emailBody");
    String cc = RunnerParamsProvider.props.getProperty("emailCC");
    String bcc = RunnerParamsProvider.props.getProperty("emailBCC");
    Properties props = new Properties();
    props.put("mail.smtp.starttls.enable", "false");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "smtp.socketlabs.com");
    props.put("mail.smtp.port", "25");
    Loggers.SERVER.info("start processing email");
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
              }
            });
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipients(Message.RecipientType.TO,
              InternetAddress.parse(to));
      message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
      message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
      message.setSubject(title);
      message.setText(body);

      Transport.send(message);
    }
    catch (MessagingException e) {
      throw new RuntimeException(e);
    }

    System.out.println("Done");
  }

  public boolean isInterrupted() {
    return false;
  }

  public boolean isFinished() {
    return false;
  }

  public void interrupt() {
  }

  @NotNull
  public BuildFinishedStatus waitFor() throws RunBuildException {
    return BuildFinishedStatus.FINISHED_SUCCESS;
  }
}
