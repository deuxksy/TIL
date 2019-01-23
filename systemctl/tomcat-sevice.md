# CentOS 7 에서 tomcat service 등록 하기

```
[Unit]
Description=Apache Tomcat 9
After=syslog.target network.target

[Service]
User=tomcat
Group=tomcat
Type=forking

Environment=JAVA_HOME=/usr/lib/jvm/jre-1.8.0
Environment=CATALINA_PID=/opt/tomcat/default/tomcat.pid
Environment=CATALINA_HOME=/opt/tomcat/default
Environment=CATALINA_BASE=/opt/tomcat/default


ExecStart=/opt/tomcat/default/bin/startup.sh
ExecStop=/opt/tomcat/default/bin/shutdown.sh

[Install] 
WantedBy=multi-user.target
```
