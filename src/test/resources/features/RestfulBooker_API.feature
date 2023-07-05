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
    And Response attribute değerlerinin "Mary", "Wilson", 254, "2015-12-25", "2023-06-05", "Breakfast" olduğu kontrol edilir


