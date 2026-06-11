LoL Rehberi Mobil Uygulaması
Bu proje, League of Legends evrenine dair dinamik bilgiler sunan ve kullanıcıların favori taktiklerini saklayabildiği Java tabanlı bir mobil uygulamadır.

Kullanıcılar, Riot Games Data Dragon API'si üzerinden şampiyon bilgilerini görüntüleyebilir, kendi taktiklerini yerel veritabanına kaydedebilir ve bu taktikleri telefonlarının ana ekranındaki özel bir widget üzerinden yönetebilirler.

Temel Özellikler
Riot Games API Entegrasyonu: ExecutorService ve HttpURLConnection yapısı kullanılarak, Riot Games sunucularından asenkron olarak gerçek zamanlı şampiyon verileri (JSON) çekilir.

SQLite Veritabanı: Kullanıcı tarafından oluşturulan özel taktikler, SQLiteOpenHelper kullanılarak cihazın yerel depolamasında kalıcı olarak saklanır.

CRUD İşlemleri: Uygulama içerisinden taktik Ekleme, Listeleme ve Toplu Silme işlemleri modern bir arayüz ile yönetilir.

İnteraktif Widget (AppWidgetProvider): Ana ekrana eklenebilen widget ile uygulama açılmadan, veritabanına kayıtlı taktiklere hızlı erişim sağlanır.

Modern ve Duyarlı Arayüz (UI/UX): Hextech temasına uygun renk paleti, ConstraintLayout, CardView ve ScrollView kullanılarak tüm cihaz boyutlarına uyumlu, ergonomik bir tasarım geliştirilmiştir.

3 Katmanlı Mimari: Uygulama; SplashActivity, MainActivity ve FavoritesActivity olmak üzere 3 farklı ekran yapısı ile tasarlanmıştır.

Kullanılan Teknolojiler
Dil: Java

Ortam: Android Studio

Minimum SDK: 24 (Android 7.0)

Veritabanı: SQLite

Ağ İşlemleri: HttpURLConnection, ExecutorService (Multi-threading)

Veri Formatı: JSON (org.json.JSONObject)

Arayüz Bileşenleri: ConstraintLayout, CardView, ScrollView

Ekran Görüntüleri

<img width="1771" height="922" alt="12" src="https://github.com/user-attachments/assets/a6e76341-2320-4638-9107-59b56d0a1a69" />

<img width="1762" height="912" alt="13" src="https://github.com/user-attachments/assets/4c19fb38-6e9e-4de2-abc5-63ef68e60e87" />


Kurulum ve Çalıştırma
Projeyi kendi bilgisayarınızda derleyip çalıştırmak için şu adımları izleyin:

Bu depoyu yerel makinenize klonlayın:
git clone https://github.com/ErolAltinok/Lol-Rehberi-Mobil 

Android Studio'yu açın ve projeyi Import Project seçeneğiyle yükleyin.

Uygulamayı bir emülatör veya fiziksel cihaz üzerinde çalıştırın.
