# LoL Rehberi Mobil Uygulaması
Bu proje, League of Legends evrenine dair dinamik bilgiler sunan ve kullanıcıların favori taktiklerini saklayabildiği Java tabanlı bir mobil uygulamadır.

Kullanıcılar, Riot Games Data Dragon API'si üzerinden şampiyon bilgilerini görüntüleyebilir, kendi taktiklerini yerel veritabanına kaydedebilir ve bu taktikleri telefonlarının ana ekranındaki özel bir widget üzerinden yönetebilirler.

## Temel Özellikler
Riot Games API Entegrasyonu: ExecutorService ve HttpURLConnection yapısı kullanılarak, Riot Games sunucularından asenkron olarak gerçek zamanlı şampiyon verileri (JSON) çekilir.

SQLite Veritabanı: Kullanıcı tarafından oluşturulan özel taktikler, SQLiteOpenHelper kullanılarak cihazın yerel depolamasında kalıcı olarak saklanır.

CRUD İşlemleri: Uygulama içerisinden taktik Ekleme, Listeleme ve Toplu Silme işlemleri modern bir arayüz ile yönetilir.

İnteraktif Widget: Ana ekrana eklenebilen widget ile uygulama açılmadan, veritabanına kayıtlı taktiklere hızlı erişim sağlanır.

Modern ve Duyarlı Arayüz (UI/UX): Hextech temasına uygun renk paleti, ConstraintLayout, CardView ve ScrollView kullanılarak tüm cihaz boyutlarına uyumlu, ergonomik bir tasarım geliştirilmiştir.

3 Katmanlı Mimari: Uygulama; SplashActivity, MainActivity ve FavoritesActivity olmak üzere 3 farklı ekran yapısı ile tasarlanmıştır.

## Kullanılan Teknolojiler
Dil: Java

Ortam: Android Studio

Minimum SDK: 24 (Android 7.0)

Veritabanı: SQLite

Ağ İşlemleri: HttpURLConnection, ExecutorService (Multi-threading)

Veri Formatı: JSON (org.json.JSONObject)

Arayüz Bileşenleri: ConstraintLayout, CardView, ScrollView  

## Ekran Görüntüleri
<img width="1771" height="922" alt="12" src="https://github.com/user-attachments/assets/6db3678d-5090-4132-8c09-d18b81064bee" />
<img width="1762" height="912" alt="13" src="https://github.com/user-attachments/assets/1367aaaf-fd63-42d6-85c7-e10bb68ea62c" />
<img width="410" height="826" alt="14" src="https://github.com/user-attachments/assets/a141877b-9b21-4944-922d-d4b689c3894b" />

### Clone
Bu depoyu yerel makinenize klonlayın:
   ```bash
   git clone [https://github.com/ErolAltinok/Lol-Rehberi-MobilUygulama.git](https://github.com/ErolAltinok/Lol-Rehberi-MobilUygulama.git)
