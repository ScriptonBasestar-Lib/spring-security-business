# spring-security-auth

## 소개

SpringSecurity 기반 인증서버 구축 도구

로그인 방법
* 아이디/비밀번호
* 화면스캔
* 2차 비밀번호
* 휴대폰 확인

인증 방법
* 브라우저 로그인 redirect - 세션
* OAuth Token
* JWT Token

사용자 종류
  * Front, Mobile 접속
  * Machine 서버나 기계적으로 접속하는경우(OAuth말고 다르게..?)


## 권한 관리

다른데서 구현.. 담을 수 있는 공간만 제공


### JWT Token의 경우

(global, local), (machine, human), (unique id)

권한 범위 authority scope : ats
* global : 한 서비스 집단 전체에 사용되는 권한\
* local : 한개의 서비스에서만 사용되는 권한
* 사용가능 서비스 목록

사용자 종류 user type : ust
* human : 사용자가 접근하는 리소스, 사용자에게 소유권\
* machine : 서버에서 접근하는 리소스, 한 서비스에 소유권

사용자 unique id
재발급 가능한 사용자 unique id


토큰의 값에서 사용자 확인 후 권한서버에서 세부 권한 확인 


### OAuth Token의 경우

사용자 토큰에서 사용자 ID 확인 - 인증서버에서 권한 확인. cache 또는 session 저장
