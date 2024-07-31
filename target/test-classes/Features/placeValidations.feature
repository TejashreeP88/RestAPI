Feature: Validating Place API's

  Scenario Outline: Verify Add Place API
    Given Add Place Payload with "<name>", "<language>" and "<address>"
    When User calls "<API Name>" with "<Method Name>" request
    Then Response is successful with status code 200
    And Validated the response body for "<Key>" and "<Value>"
    And Verify the place id created for the place "<name>" using "<Get API>"

    Examples: 
      | API Name    | Method Name | Key    | Value | name    | language | address            | Get API     |
      | AddPlaceAPI | POST        | status | OK    | AAHouse | English  | World Cross Center | GetPlaceAPI |

	@DeletePlace
  Scenario: Verify if Delete API is working as expected
    Given Delete Place Payload
    When User calls "DeletePlaceAPI" with "Delete" request
    Then Response is successful with status code 200
    And Validated the response body for "status" and "OK"
