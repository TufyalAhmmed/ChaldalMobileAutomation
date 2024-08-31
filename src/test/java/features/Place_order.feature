Feature: Search Add Cart

  Scenario: Verify Search,product select & cart of Chaldal app
    Given Search for the item toothbrush
    When Scroll down to an item and Open the Item screen
    And Click the Plus icon three times to add to the cart
    And Go to the cart screen from the top
    And Click the Minus icon to empty the card
    Then Verify Text Nothing to see here on cart screen after removing items
