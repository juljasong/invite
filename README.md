# Jar 경로
* https://github.com/juljasong/invite/blob/main/invite-0.0.1-SNAPSHOT.jar

# API 명세
**참여자 초대**

* **URL**

  /user/invite

* **Method:**

  `POST` 

*  **URL Params**

   None

* **Data Params**

  `{"name": "테스트", "tel": "010-1111-2222", "email": "test@gmail.com"}`

* **Success Response:**

    * **Code:** 200 <br />
      **Content:**
       ```
      http://localhost:8080/user/invite/c5ddc1e4-4060-47bc-bd0d-a84979c6f6d6
       ```

* **Error Response:**

    * **Code:** 400 Bad Request<br />
      **Content:**
      ```
        {
        "code": "400",
        "message": "잘못된 요청입니다.",
        "validation": {
            "name": "이름을 입력해 주십시오.",
            "tel": "전화번호를 입력해 주십시오.",
            "email": "이메일 주소를 입력해 주십시오."
             }
        }
      ```

**초대 수락**
----

* **URL**

  /user/invite/:code

* **Method:**

  `GET`

*  **URL Params**

   None

* **Data Params**

  None

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:**
       ```
      OK
       ```

* **Error Response:**

    * **Code:** 400 Bad Request<br />
      **Content:**
      ```
        {
          "code": "400",
          "message": "유효하지 않은 코드 입니다.",
          "validation": {}
         }
      ```

* **Sample Call:**

     ```
        $curl http://localhost:8080/user/invite/c5086d00-3155-4c24-b62e-6e62a5d357ce
     ```