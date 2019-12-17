# WhisperServer SpringBoot使用 RSA + AES 对通讯双向加密的服务示例

### 项目目录结构
``` shell
.
|____pom.xml
|____WhisperServer.iml
|____.gitignore
|____keys
| |____rsa_public_key_pem
| |____rsa_private_key_pem
|____src
| |____test
| | |____java
| | | |____com
| | | | |____bloodsport
| | | | | |____whisper
| | | | | | |____WhisperApplicationTests.java
| |____main
| | |____resources
| | | |____static
| | | |____templates
| | | |____application.properties
| | |____java
| | | |____com
| | | | |____bloodsport
| | | | | |____whisper
| | | | | | |____core
| | | | | | | |____util
| | | | | | | | |____EncodeUtils.java
| | | | | | | | |____EncryptUtils.java
| | | | | | | |____wrapper
| | | | | | | | |____GlobalExceptionWrapper.java
| | | | | | | | |____GlobalApiResultWrapper.java
| | | | | | | |____annotation
| | | | | | | | |____SecurityParameter.java
| | | | | | | | |____Whisper.java
| | | | | | | | |____ResultSkip.java
| | | | | | | |____support
| | | | | | | | |____GsonSupportHandler.java
| | | | | | | |____exception
| | | | | | | | |____CustomException.java
| | | | | | |____example
| | | | | | | |____KeyValueDatabase.java
| | | | | | | |____MemoryKeyValueDatabase.java
| | | | | | |____model
| | | | | | | |____UserTokenModel.java
| | | | | | | |____ResultStateEnum.java
| | | | | | | |____BaseResultDTO.java
| | | | | | | |____pojo
| | | | | | | | |____PublicKeyDTO.java
| | | | | | | | |____UserDTO.java
| | | | | | |____feature
| | | | | | | |____usercenter
| | | | | | | | |____UserCenterController.java
| | | | | | | |____encrypt
| | | | | | | | |____wapper
| | | | | | | | | |____GlobalEncryptionWrapper.java
| | | | | | | | | |____GlobalDecryptWrapper.java
| | | | | | | | |____EncryptionController.java
| | | | | | |____interf
| | | | | | | |____TokenHelper.java
| | | | | | |____helper
| | | | | | | |____JsonSerializer.java
| | | | | | | |____JwtTokenHelper.java
| | | | | | |____WhisperApplication.java

```
