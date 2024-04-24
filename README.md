[Türkçe Açıklama için tıklayınız](#TR)

# Trip Scanner

Backend API for a train ticket search application.

## Used Technologies

- **Spring Boot**
- **Spring Boot Data JPA**
- **Spring Boot Security**
- **Spring Boot Web**
- **PostgreSQL JDBC Driver**
- **Lombok**
- **Spring Security Test**
- **Jakarta Validation API**
- **Springdoc OpenAPI Starter**

## General Structure

- Data Modeling
- CRUD operations
- Exposing services via REST
- Java (Spring Boot)
- Authentication structure
- Scheduled background jobs
- API documentation with Swagger

## General Details

- A Result structure designed to be more understandable and user-friendly for front-end developers.
- ModelMapper reduces code complexity by providing automatic transformation between DTO and Entity objects.
- ModelMapper can be customized according to needs.
- Request-Response model.
- ID values are automatically converted to unique UUID strings.
- ZonedDateTime is always used for time data types considering the importance of whether the departure/arrival times and dates of train tickets are based on local time.

## STATION FEATURES

- Four endpoints accessible only by Admin and Root users.
- Endpoints perform Update, Delete, GetAll, and Add operations.
- While the deletion of a station is rare, a delete method is provided for scenarios like station maintenance.
- The same station cannot be added multiple times.
- When adding a station, the "name" field is stored in uppercase and is processed to be compatible with Turkish characters.

## TRIP FEATURES

- Trip methods include Update, Add, GetAll, and Delete functions accessible only by Admin and Root users. Additionally, there are two functions: SearchApi accessible to all users and fetchFromMockApi accessible to specific users, making a total of 6 functions.

### Add Features

1. **Time Travel Prohibited**
    - You cannot go back in time.
    - The departure or return date cannot be before today.

2. **No Return to Return**
    - The return date cannot be before the departure date.
    - You cannot return before reaching your destination.

3. **No Free Travel**
    - The price cannot be free or negative.
    - Every trip has a cost.

4. **No Fictional Stations**
    - Station existence is checked.
    - You cannot add a trip to a non-existing station.

5. **Station Name is Station ID**
    - The station ID can only contain text.
    - Numbers or symbols are not accepted.

6. **Same Departure and Arrival Not Allowed**
    - Departure and arrival stations cannot be the same.
    - You cannot depart from and arrive at the same place.

### Update Features

1. **Which Information Can Be Updated?**
    - Date: Since departure and arrival points of a trip are generally fixed, only the date information can be changed.
    - Price: If there is a change in trip prices, this information can be updated.

2. **Which Information Cannot Be Updated?**
    - Trip ID: It is not possible to update information with a non-existent trip ID.
    - Departure and Arrival Points: Since the departure and arrival points of a trip are generally fixed, these details cannot be changed.

### Delete Features

- Accepts only an ID and deletes the trip associated with that ID.
- If there is no trip with the entered ID, the operation fails.
- You cannot enter a value that is not a string as an ID.

### GetAll Features

- Uses the ModelMapper library to transform received Flight objects into DTOs.
- Transfers the transformed DTOs as a list to the result.

## SearchApi Features

- SearchApi cannot search in the past, which poses a security risk.
- SearchApi provides possible travel options by making a 48-hour search including 24 hours before and after the specified date for both departure and return dates.
- If only the departure date is specified, SearchApi brings only one-way trips. However, if the return date is also specified, it performs a round-trip search.
- Since SearchApi is a travel search algorithm, the "/searchapi" endpoint is found in FlightController.
- If there is no direct travel from point A to point B, but there is a return trip from B to A, the return trip is presented as a departure trip and its price is halved.
- Case-insensitive search; for example, a city name written like "aNkARa" is considered as "ANKARA".
- Recognizes Turkish characters and can search with these characters.

## Authentication

- Authentication is made using DataSource, not Inmemory. All users are registered in the database. There is no default user, and therefore no user information can be found in the application.properties file.
- While Swagger UI can be viewed by everyone, authentication is required to access endpoints.
- Traditional HttpBasic Authentication method is preferred.
- Authorization structure is as follows: Root > Admin > User

## USER FEATURES

- **Root Access**: Only the Root user can access 3 endpoints in this system.
- Each user's username must be **unique**.
- **Admin or User**: Users can choose one of the Admin or User roles.
- **Case Insensitivity for Admin and User**: Admin and User are case-insensitive (for example, usEr or aDMiN will be accepted and converted to uppercase before being saved).
- **Passwords** are securely stored using the Bcrypt encryption algorithm.
- **isActive Column**: A user cannot be deleted; instead, a "soft delete" is performed using the isActive column.

## FETCH from MOCK API

- At 18:00 every day, the application retrieves a TripSaveRequestDTO[] array from a third-party Rest API and converts this data to a Flight object.
- This method is located within the TripBusiness component and can be called from other components. It is managed through the Inversion of Control (IOC) mechanism of the application and continues to make requests to the API at the specified time.

## ENDPOINTS
![image](https://github.com/iumutdikbasan/TripScanner/assets/54438200/d1d36c79-ff5c-45a2-9167-0038c3fb40a7)
# TR
# Trip Scanner

Bir tren bileti arama uygulaması için backend API.

## Kullanılan Teknolojiler

- **Spring Boot**
- **Spring Boot Data JPA**
- **Spring Boot Security**
- **Spring Boot Web**
- **PostgreSQL JDBC Driver**
- **Lombok**
- **Spring Security Test**
- **Jakarta Validation API**
- **Springdoc OpenAPI Starter**

### Genel Yapı

- Data Modeling
- CRUD yapısı
- REST ile dışarıya servis sunulması
- Java(Spring Boot)
- Authentication yapısı
- Scheduled background joblar
- Swagger ile API dokümantasyon

### Genel Detaylar

- Front-end Geliştiriciler için daha anlaşılır ve kullanımı kolay bir "Result" yapısı tasarlandı.
- ModelMapper, DTO ve Entity nesneleri arasında otomatik dönüştürme sağlayarak kod karmaşıklığını azaltır.
- ModelMapper, ihtiyaçlara göre uyarlanabilir.
- Request-Response modeli.
- ID değerler, otomatik olarak benzersiz UUID'lerden oluşan Stringlere dönüştürülerek oluşturulmuştur.
- Tren biletlerinin iniş/biniş saat ve gün bilgilerinin hangi ülkenin saatine göre olduğu önem arz ettiği gözetildiğinden zaman veri tiplerinde her zaman ZonedDateTime kullanılmıştır.


## STATION ÖZELLİKLERİ

- Sadece Admin ve Root kullanıcıları tarafından erişilebilen dört endpoint bulunmaktadır.
- Endpointler Update.Delete.GetAll ve Add işlemlerini gerçekleştirir.
- Bir istasyonun yıkılması durumu oldukça nadirdir. Yine de istasyon bakımı gibi sebeplerden ötürü istasyon kullanılamaz halde olabileceğinden delete metotu oluşturulmuştur. 
- Aynı istasyon birden fazla kez sisteme eklenemez.
- İstasyon eklenirken "name" alanı büyük harflerle kaydedilmekte ve Türkçe karakterlere uyumlu bir şekilde işlenmektedir.

## TRIP ÖZELLİKLERİ

- Trip metodları, yalnızca Admin ve Root kullanıcıların erişebildiği  Update, Add, GetAll ve Delete işlevlerini içerir. Ayrıca, tüm kullanıcılar için erişilebilir olan "SearchApi" ve sadece belirli kullanıcılar için olan "fetchFromMockApi" fonksiyonlarıyla birlikte toplamda 6 farklı işlevi bulunmaktadır.

#### Add Özellikleri
1. **Zaman Yolculuğu Yasak**
    - Geçmişe gidemezsiniz. 
    - Gidiş veya dönüş tarihi bugünden önce olamaz.

2. **Dönüş Dönüş Olmaz**
    - Dönüş tarihi, gidiş tarihinden önce olamaz. 
    - Gideceğiniz yere gitmeden geri dönemezsiniz.

3. **Ücretsiz Seyahat Yok**
    - Fiyat ücretsiz veya negatif olamaz. 
    - Her seyahatin bir bedeli vardır.

4. **Hayali İstasyon Yasak**
    - İstasyon varlığını kontrol ediyoruz. 
    - Olmayan bir istasyona seyahat ekleyemezsiniz.

5. **İstasyon Adı İstasyon ID'si Demektir**
    - İstasyon ID'si sadece metin içerebilir. 
    - Rakam veya sembol kabul edilmez.

6. **Aynı İstasyondan Kalkış ve İniş Olmaz**
    - Kalkış ve iniş istasyonları aynı olamaz. 
    - Aynı yerden kalkıp aynı yere inemezsiniz.
  
#### Update Özellikleri

1. **Hangi Bilgiler Güncellenebilir?**
    - Tarih: Seyahatin kalkış ve iniş yerleri genellikle sabit olduğundan, sadece tarih bilgisi değiştirilebilir.
    - Fiyat: Seyahat fiyatlarında değişiklik olması durumunda bu bilgi güncellenebilir.

2. **Hangi Bilgiler Güncellenemez?**
    - Seyahat ID: Var olmayan bir seyahatin ID'si ile bilgi güncellemek mümkün değildir.
    - Kalkış ve İniş Yerleri: Seyahatin kalkış ve iniş noktaları genellikle sabit olduğundan bu bilgiler değiştirilemez.
   
#### Delete Özellikleri

- Sadece bir ID girmenizi kabul eder ve o ID ile ilişkili seyahati siler.
- Girdiğiniz ID'ye sahip bir seyahat yoksa, işlem başarısız olur.
- ID olarak string olmayan bir değer giremezsiniz.

#### GetAll Özellikleri

- ModelMapper kütüphanesini kullanarak aldığı Flight nesnelerini DTO'ya dönüştürür.
- Dönüştürülen DTO'ları bir liste halinde sonuca aktarır.

---

## SearchApi Özellikleri

- SearchApi'nin geçmişte arama yapması mümkün değildir, bu güvenlik riski oluşturur.
- SearchApi, belirtilen tarihten 24 saat önce ve sonrasını da içeren 48 saatlik bir arama yaparak, kullanıcıya olası seyahat seçeneklerini sunar, hem gidiş hem de dönüş tarihi için.
- Eğer sadece gidiş tarihi belirtildiyse, SearchApi yalnızca tek yönlü seyahatleri getirir, ancak dönüş tarihi de belirtildiyse çift yönlü arama yapar.
- SearchApi'nin bir seyahat arama algoritması olduğu için, "/searchapi" endpointi FlightController'da bulunur.
- A noktasından B noktasına doğrudan seyahat olmadığında, ancak B'den A'ya dönüş seyahati varsa, dönüş seyahati gidiş seyahati olarak sunulur ve fiyatı yarıya indirilir.
- Harf büyüklüğüne duyarlı olmayan bir arama yapar; örneğin, "aNkARa" gibi yazılan bir şehir ismi "ANKARA" olarak kabul edilir.
- Türkçe karakterleri tanır ve bu karakterlerle de arama yapabilir.

  ---
## Authentication

- Inmemory değil DataSource kullanılarak yapılmıştır. Tüm kullanıcılar Database’de kayıtlıdır. Varsayılan kullanıcı yoktur ve bu yüzden application.properties içerisinde kullanıcı bilgisi bulunamaz.
- Swagger UI herkes tarafından görülebilir, fakat endpointlere erişim için kimlik doğrulama gereklidir.
- Geleneksel HttpBasic Authentication yöntemi tercih edilmiştir.
- Yetkilendirme yapısı şu şekildedir: Root > Admin > User

## USER ÖZELLİKLERİ

- **Root Erişimi**: Bu sistemde sadece Root kullanıcısı 3 endpoint'e erişim sağlayabilir.
- Her kullanıcının kullanıcı adı **unique** olmalıdır.
- **Admin veya User**: Kullanıcılar Admin veya User rollerinden birini seçebilir.
- **Büyük/Küçük Harf Duyarsızlık**: Admin ve User büyük/küçük harf duyarsızdır (örneğin, usEr veya aDMiN kabul edilecek ve kaydedilmeden önce büyük harflere dönüştürülecektir).
- **Parolalar**, Bcrypt şifreleme algoritması kullanılarak güvenli bir şekilde saklanır.
- **isActive Sütunu**: Bir kullanıcı silinemez, bunun yerine isActive sütunu kullanılarak "yumuşak silme" işlemi gerçekleştirilir.

## FETCH from MOCK API

- Saat 18.00'de, her gün, uygulama, dışarıdan gelen TripSaveRequestDTO[] dizisini bir third-party Rest API'den alır ve bu veriyi Flight nesnesine dönüştürür.
- Bu metod, TripBusiness bileşeni içinde yer alır ve diğer bileşenlerden çağrılabilir. Uygulama IOC (Inversion of Control) mekanizmasıyla yönetilir ve çalıştığı sürece belirtilen saatte API'ya istek yapmaya devam eder.

---
## ENDPOINTLER 
![image](https://github.com/iumutdikbasan/TripScanner/assets/54438200/d1d36c79-ff5c-45a2-9167-0038c3fb40a7)
