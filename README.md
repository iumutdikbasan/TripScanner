# TripScanner

Bir tren bileti arama uygulaması için backend API.

### Genel Yapı

- Data Modeling
- CRUD yapısı
- REST ile dışarıya servis sunulması
- Java(Spring Boot)
- Authentication yapısı
- Scheduled background joblar
-  Swagger ile API dokümantasyon

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
    - Olmayan bir istasyona uçuş ekleyemezsiniz.

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


