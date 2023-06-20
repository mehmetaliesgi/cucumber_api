Feature: Kullanıcı JPH endpoint'ine Post request gönderilir.

  Scenario: Kullanıcı Post request sonucu dönen response değerini kontrol eder
    Given Kullanici "jPHBaseUrl" base URL'ini kullanir
    Then Path parametreleri icin "posts" kullanir
    And Post request icin "Mehmet", "Merhaba", 10, 101 bilgileri ile request body olusturulur
    And jPH server'a POST request gonderilir ve testleri yapmak icin response degerini kaydeder
    Then jPH respons'da status degerinin 201
    And jPH respons'da content type degerinin "application/json; charset=utf-8"
    And jPH response'daki "Connection" header degerinin "keep-alive" oldugunu test et
    Then response attributelerini degerlerinin "Mehmet","Merhaba",10,101 olduğunu kontrol et
