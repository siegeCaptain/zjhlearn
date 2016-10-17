
@echo off
cd ../..
mvn  -am -pl ma-account-service,ma-contact-service,ma-core-service,ma-subscriber-service,ma-wechat-actionservice,ma-sms-actionservice,ma-datachange-listener,carowner-lifecycle package

xcopy ma-account-service\target\ma-account-service-0.1.jar  release\target /y
xcopy ma-contact-service\target\ma-contact-service-0.1.jar  release\target /y
xcopy ma-core-service\target\ma-core-service-0.1.jar  release\target /y
xcopy ma-datachange-listener\target\ma-datachange-listener-0.1.jar  release\target /y
xcopy ma-sms-actionservice\target\ma-sms-actionservice-0.1.jar  release\target /y
xcopy ma-subscriber-service\target\ma-subscriber-service-0.1.jar  release\target /y
xcopy ma-wechat-actionservice\target\ma-wechat-actionservice-0.1.jar  release\target /y
xcopy carowner-lifecycle\target\carowner-lifecycle-0.1.jar  release\target /y

cd release
cd dev-windows