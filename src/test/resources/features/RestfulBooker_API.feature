Feature: Kullanıcı Restful-booker endpoint'inin testini gerçekleştirir.

  Background:
    Given Kullanici "RestfulBooker" base URL'ini kullanir

  Scenario: Kullanıcı GET isteği yaparak bütün rezervasyonları görüntüler
    And Path parametreleri icin "booking" kullanir
    And GET requesti yapılır ve response degerini kaydeder
    Then Request sonucu response'da status degerinin 200 olduğu kontrol edilir


  Scenario: Kullanıcı GET isteği yaparak spesifik bir rezervasyonu görüntüler
    And Path parametreleri icin "booking/7" kullanir
    When GET requesti yapılır ve response degerini kaydeder
    Then Request sonucu response'da status degerinin 200 olduğu kontrol edilir
    And Response attribute değerlerinin "Mark", "Ericsson", 658, "2020-06-10", "2021-10-18", "Breakfast" olduğu kontrol edilir


  Scenario: Kullanıcı POST isteği yaparak yeni bir rezervasyon oluşturur
    And Path parametreleri icin "booking" kullanir
    And Request için gerekli veriler girilir ve body oluşturulur, "Jim", "Brown", 112, "TRUE", "2018-01-01", "2019-01-01", "Wi-Fi"
    When POST requesti yapılır ve response değeri kaydedilir
    And Request sonucu response'da content type degerinin "Content-Type: application/json"
    Then Request sonucu response'da status degerinin 200 olduğu kontrol edilir


  Scenario: Kullanıcı PUT isteği yaparak var olan bir kayıt üzerinde değişiklik yapar
    And Kullanıcı bir token üretir
    And Path parametreleri icin "booking/7053" kullanir
    And Request için gerekli veriler girilir ve body oluşturulur, "Jim1", "Brown1", 1121, "TRUE", "2018-04-01", "2019-01-01", "B2B"
    When PUT requesti yapılır ve response değeri kaydedilir
    And Request sonucu response'da content type degerinin "Content-Type: application/json"
    Then Request sonucu response'da status degerinin 200 olduğu kontrol edilir


  Scenario: Kullanıcı DELETE isteği yaparak var olan bir kayıdı siler
    And Path parametreleri icin "booking/1918" kullanir
    When DELETE requesti yapılır ve response değeri kaydedilir
    Then Request sonucu response'da status degerinin 201 olduğu kontrol edilir