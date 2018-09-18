Feature: NonceFilterCheck

  Scenario: check nonce filter
    Given create default nonce filter
    When without any constructor
    Then exception will occured.