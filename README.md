## 概要

Spring Security で認証（ログイン・ログアウト）を行うサンプルです。  
インメモリ認証とユーザテーブルによる認証が可能です。

## 画面

#### 初期表示
<img width="600" src="https://user-images.githubusercontent.com/59589496/131136943-c508a591-8854-4276-a35f-d0b958eab125.png">  

#### H2 コンソール
USERSテーブル  
<img width="700" src="https://user-images.githubusercontent.com/59589496/131137699-ae5100b1-25e5-4fa9-9d06-c1db0b80e9b6.png">  

#### ログイン認証失敗
<img width="600" src="https://user-images.githubusercontent.com/59589496/131138148-0bfdaae5-c60c-43b7-a4f5-f2c06c61c427.png">  

#### ログイン認証成功
<img width="600" src="https://user-images.githubusercontent.com/59589496/131138525-b68274dc-3bf0-421f-840e-ea9a53dc727c.png">  
<img width="600" src="https://user-images.githubusercontent.com/59589496/131138755-7d553024-3e16-4fb0-8643-bfa078935739.png">  

#### ログアウト
<img width="600" src="https://user-images.githubusercontent.com/59589496/131139018-e90f3310-63da-4cb1-b8b9-40efa32e8f37.png">  
<img width="600" src="https://user-images.githubusercontent.com/59589496/131139090-084a69e6-2086-429b-aafd-8a3a7b20e2ed.png">  

## フォルダ構成

<img width="320" src="https://user-images.githubusercontent.com/59589496/131139604-d9019ae0-97a0-47d2-89eb-9e648ffa635c.png">  

## 依存関係

* Spring Boot DevTools
* Thymeleaf
* Spring Web
* Validation
* Spring Data JPA
* H2 Database
* Spring Security
* Lombok
* WebJars
  - Bootstrap
  - Bootstrap Icons
