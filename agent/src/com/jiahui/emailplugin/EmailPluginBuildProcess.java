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

import com.intellij.openapi.util.text.StringUtil;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.log.Loggers;
import org.jetbrains.annotations.NotNull;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class EmailPluginBuildProcess implements BuildProcess {
  private final BuildRunnerContext myContext;
  final String username = "username";
  final String password = "password";


  public EmailPluginBuildProcess( @NotNull final BuildRunnerContext context){
    myContext = context;
  }

  public void start() throws RunBuildException {
    Map<String, String> runnerParams = new HashMap<String, String>(myContext.getRunnerParameters());
    Loggers.AGENT.info("start getting parameters");
    for(Map.Entry<String, String> entry : runnerParams.entrySet()){
      RunnerParamsProvider.setProperty(entry.getKey(), entry.getValue());
    }
    Loggers.AGENT.info("start extracting parameters");
    String title = RunnerParamsProvider.props.getProperty("emailTitle");
    String from = RunnerParamsProvider.props.getProperty("emailFrom");
    String to = RunnerParamsProvider.props.getProperty("emailTo");
    String body = RunnerParamsProvider.props.getProperty("emailBody");
    String cc = RunnerParamsProvider.props.getProperty("emailCC");
    String bcc = RunnerParamsProvider.props.getProperty("emailBCC");
    Loggers.AGENT.info(title);
    Properties props = new Properties();
    props.put("mail.smtp.starttls.enable", "false");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "smtp host");
    props.put("mail.smtp.port", "25");
    props.put("mail.debug", "true");
    Loggers.AGENT.info("start processing email");
    javax.mail.Session session = javax.mail.Session.getInstance(props,
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
      if(!StringUtil.isEmptyOrSpaces(cc)) {
        message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
      }
      if(!StringUtil.isEmptyOrSpaces(bcc)) {
        message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
      }
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
