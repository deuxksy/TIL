# URL 설계

## 가이드

- session 없음 보안은 http header 에 인증정보를 넣음 https 만 사용
  - Authorization: Basic QWxhxxxxxxxxxxxxxx==
  - Schemename:space:Scheme-specific Value
- 문서화 테스트 Swagger, Spring Rest Doc
- API 인증 방식
로그인을 하면 api token 을 발급 하여서 api 통신시 token 일정 시간이 지나면 expiration  
- URL 보고 무슨 API 인지 쉽고 직관적
- URL 은 너무 깊게 만들지 말자
- 동사 보다는 명사
  - /getDog
  - /setDog
- 단수 보다는 복수형
  - /dogs
  - /dog/happy
- http method
  - GET: 조회(R) /products
  - POST: 생성(C) /products
  - PUT or  PETCH : 수정(U) /products/1
  - DELETE: 삭제(D) products/1
  
|URL|GET|POST|PUT|DELETE|
|---|---|---|---|---|
|/products|상품목록 조회|상품 생성| ~~전체상품 수정~~ | ~~전체상품 삭제~~ |
|/products/1|지정상품 조회|~~상품 생성~~|지정상품 수정| 지정상품 삭제 |

- response code
  - 200: 성공
  - 400: field 유용하지 않음
  - 401: 인증 실패
  - 402: 결제가 필요
  - 403: 권한 없음
  - 404: 니가 알던 그거
  - 500: 오류
  - [MDN-HTTP 상태 코드](https://developer.mozilla.org/ko/docs/Web/HTTP/Status) 참조 해서 추가 하기
- response body 다국어 처리하기
```json
{
  "errors": [{
    "code": 20003,
    "message": "",
    "detail": "",
    "more": "http://api.server.com/common/v1/errors/20003"
  }]
}
```
- /url/group/sub/
- 버전관리
api.server.com/{서비스명}/{버번}/{리소스}
api.server.com/product/v1/wish
- 페이징 (100번째 레코드 부터 25개 출력)
api.server.com/product/v1/wish?offset=100&limit=25  
전체 개수는 custom http header X-Total-Count
- Rest API 중 일부만 응답 받기
api.server.com/product/v1/wish?fields=name,price,seller.name
- 검색 query string
  - api.server.com/product/v1/search?color=red
  - api.server.com/product/v1/search?sort=-price,+hit
  - api.server.com/product/v1/search?q=keyword=%25EC%2599%25B8%25EB%25A1%259C%25EC%259B%258C,brand=CEE&offset=100&limit=25
- 전체, 지역 검색
  - api.server.com/product/v1/search?q=keyword=%25EC%2599%25B8%25EB%25A1%259C%25EC%259B%258C,order=1,brand=CEE&offset=100&limit=25
  - api.server.com/product/v1/brand?q=keyword=%25EC%2599%25B8%25EB%25A1%259C%25EC%259B%258C,order=1,brand=CEE&offset=100&limit=25
- HATEOS 다른 리소스, 연관 정보 를 링크에 제공
api.server.com/product/v1/wish?offset=100&limit=25
```json
{  
  "product":[
    {
      "name": "드레스셔츠",
      "price": 27600,
      "links": [
        {"rel": "seller", "href": "http://www.halfclub.com/Brand?BrdCd=PWY&PrdType=0&Category=ak"}
      ]
    }    
  ],
  "links": [
    {"rel": "first_page", "href": "api.server.com/product/v1/wish?offset=0&limit=5"},
    {"rel": "pre_page", "href": "api.server.com/product/v1/wish?offset=6&limit=5"},
    {"rel": "next_page", "href": "api.server.com/product/v1/wish?offset=11&limit=5"},
    {"rel": "last_page", "href": "api.server.com/product/v1/wish?offset=122&limit=5"},
  ]
}
```
- 단일 서버 API URL CORS 문제
  - reverse proxy
  - netflix zuul
  - aws alb 
  - graphql 이거는 아직 감 안잡힘 

## 참조
- [그런 REST API 로 괜찬은가?](https://www.youtube.com/watch?v=RP_f5dMoHFc)
- [Archer Yoo의 RESTful API 설계](https://www.slideshare.net/brotherjinho/restful-api-64494716)
- [조대협의 REST API 설계](https://www.slideshare.net/Byungwook/rest-api-60505484)
- [조대협의 REST API의 이해와 설계-#3 API 보안](http://bcho.tistory.com/955?category=252770)
- [nexusz99 REST API 디자인 개요](https://www.slideshare.net/nexusz99/rest-api-48600643)
- [Stormpath Design Beautiful REST + JSON APIs](https://www.slideshare.net/stormpath/rest-jsonapis)
- [백기선의 REST API Development with Spring](https://www.slideshare.net/whiteship/rest-api-development-with-spring)
  - [Sample Project](https://github.com/keesun/study/tree/master/ksug201811restapi)
