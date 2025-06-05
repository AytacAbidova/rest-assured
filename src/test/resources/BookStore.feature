Feature: Create user
  Scenario:
    Given "Aytac1234" and "Qwerty@12356" daxil et
    And header "Content-Type", "application/json" gonderilir
    When send request to "User" endpoint
    Then user yarandi
    And status code 201 olmalıdır
