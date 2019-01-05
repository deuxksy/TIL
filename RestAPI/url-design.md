# URL 설계

## 가이드

권한에 대한 부분이 부족함

1. URL 보고 무슨 API 인지 쉽고 직관적
1. URL 은 너무 깊게 만들지 말자
1. 동사 보다는 명사
  - /getDog
  - /setDog
1. 단수 보다는 복수형
  - /dogs
  - /dog/happy
1. response code
  - 200: 성공
  - 400: field 유용하지 않음
  - 401: 인증 실패
  - 404: 니가 알던 그거
  - 500: 오류
1. response body 다국어 처리하기
```json
{
  "error": 20003,
  "message": "",
  "detail": ""
}
```
1. 버전관리
api.server.com/{서비스명}/{버번}/{리소스}
api.server.com/product/v1/wish
1. 페이징 (100번째 레코드 부터 25개 출력)
api.server.com/product/v1/wish?offset=100&limit=25
1. Rest API 중 일부만 응답 받기
api.server.com/product/v1/wish?fields=name,price,seller.name
1. 검색 query string
api.server.com/product/v1/search?q=keyword=%25EC%2599%25B8%25EB%25A1%259C%25EC%259B%258C,order=1,brand=CEE&offset=100&limit=25
1. 전체, 지역 검색
api.server.com/product/v1/search?q=keyword=%25EC%2599%25B8%25EB%25A1%259C%25EC%259B%258C,order=1,brand=CEE&offset=100&limit=25
api.server.com/product/v1/brand?q=keyword=%25EC%2599%25B8%25EB%25A1%259C%25EC%259B%258C,order=1,brand=CEE&offset=100&limit=25
1. HATEOS 다른 리소스, 연관 정보 를 링크에 제공
api.server.com/product/v1/wish?offset=100&limit=25
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
    {"rel": "pre_page", "href": "api.server.com/product/v1/wish?offset=6&limit=5"},
    {"rel": "next_page", "href": "api.server.com/product/v1/wish?offset=11&limit=5"},
  ]
}
1. 단일 서버 API URL CORS 문제
  - reverse proxy
  - netflix zuul
  - aws alb 
  - graphql 이거는 아직 감 안잡힘 
1. API 인증 방식
로그인을 하면 api token 을 발급 하여서 api 통신시 token 일정 시간이 지나면 expiration  

## 참조

[조대협의 REST API 설계](ttps://www.slideshare.net/Byungwook/rest-api-60505484)
