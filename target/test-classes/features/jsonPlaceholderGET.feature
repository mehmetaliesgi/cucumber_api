Feature: Jsonplaceholder endpoint'inde GET request testi yapar
  @ApiGet
  Scenario: TC01 44 numaralı endpoint'e get request gönderip testleri yapar

    Given Kullanici "" base URL'ini kullanir
    Then Path parametreleri icin "" kullanir
    And jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder
    Then jPH respons'da status degerinin {statusCOde}
    And jPH respons'da content type degerinin {contentType}
    Then jPH GET respons body'sinde {attribute} degerinin Integer {expectedValue}
    And jPH GET respons body'sinde {attribute} degerinin String {expectedValue}

