Feature: Kullanıcı Restful-booker endpoint'inin testini gerçekleştirir.

  Background:
    Given Kullanici "RestfulBooker" base URL'ini kullanir

  Scenario: Kullanıcı GET isteği yaparak bütün rezervasyonları görüntüler
    And Path parametreleri icin "booking" kullanir
    And GET requesti yapılır ve response degerini kaydeder
    Then Request sonucu response'da status degerinin 200 olduğu kontrol edilir



