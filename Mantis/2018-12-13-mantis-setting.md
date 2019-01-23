# Mantis 설정

설치 버전 2.13.1  

## 설치 오류

생성시 SQL 오류 발생 무시 하고 넘어가면 상관 없음  
아래 SQL 두번 실해하는거 같음... 왜 그럴까?  

```sql
ALTER TABLE mantis_bug_revision_table ADD  INDEX idx_bug_rev_id_time  (bug_id, timestamp);
```
## 로그인 및 주의 사항

default 관리자 ID: administrator  
default 관리자 PW: root  

설치 완료후 2가지 주의사항 

- administrator 비밀번호 변경
- ${mantis_home}/admin 삭제

## 메일 발송 설정

