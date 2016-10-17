# 说明:
 ## spring-cloud-config项目是spring cloud config server, 跟marketing-automation项目放在同一路径下;
 ## springboot-config是配置文件所在的git仓库(配置用git管理),取到服务器所在的 /data目录下;

# clone & startup spring cloud config server

git clone http://code.boldseas.com/automation/spring-cloud-config.git
cd spring-cloud-config
git checkout 1.0.2.RELEASE
mvn -Dmaven.test.skip package

## 拷贝如下文件到 release/target目录:
mkdir -p ../marketing-automation/release/target
cp spring-cloud-config-server/target/spring-cloud-config-server-1.0.2.RELEASE-exec.jar ../marketing-automation/release/target/

##  git URI配置从这里取到
cd /data/
git clone http://code.boldseas.com/automation/springboot-config.git

## 配置config server获取配置的git URI, URI可以是本地目录, 编辑git URI
cd marketing-automation/release/
vim dev/config/config-server-bootstrap.yml

## 启动config server(配置服务器):
cd marketing-automation/release/dev
source config-dev-server.sh.bat

配置服务启动后可以通过URL查看配置服务器上各个项目/环境下的配置, 比如查看account项目开发环境的配置:
    http://127.0.0.1:9001/account/dev

# 打包 automation 相关项目:
 mvn -am -pl ma-account-service,ma-contact-service,ma-core-service,ma-subscriber-service,ma-wechat-actionservice package


 cp ma-account-service/target/ma-account-service-0.1.jar release/target/
 cp ma-contact-service/target/ma-contact-service-0.1.jar release/target/
 cp ma-core-service/target/ma-core-service-0.1.jar release/target/
 cp ma-wechat-actionservice/target/ma-wechat-actionservice-0.1.jar release/target/
 cp ma-subscriber-service/target/ma-subscriber-service-0.1.jar release/target/

# 启动 release/dev/目录下的dev-xxx.sh.bat文件:
source dev-account-service.sh.bat
source dev-contact-service.sh.bat
source dev-core-service.sh.bat
.....

服务启动完后, 可以通过/env路径来查看变量:
    http://127.0.0.1:7001/env
