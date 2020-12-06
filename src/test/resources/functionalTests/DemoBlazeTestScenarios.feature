
Feature: A User should be able to purchase items from website

  Scenario: The User should be able to Open the website
    Given I launch the website 
    
  Scenario: The User should be able to navigate to laptop products    
    When I click on laptop under product categories
    Then I expect to see laptop "sony_vaio_i5"
    
  Scenario: The User should be able to add item to cart  
    When I open "sony_vaio_i5" from catalog
     And  I add item to cart
    Then I expect to see product added alert 
    When I close the alert 
     And I click on home button
    Then I expect to navigate to home screen
    When I click on laptop under product categories
    Then I expect to see laptop "Dell_i7_8gb"
    When I open "Dell_i7_8gb" from catalog
     And  I add item to cart
    Then I expect to see product added alert 
    When I close the alert 
    When I click on cart button
    Then I expect to see added items 
    
   Scenario: The User should be able to delete cart item 
    When I delete "Dell_i7_8gb" from cart
    Then I expect to not see "Dell_i7_8gb" in the cart
    
   Scenario: The User should be able to place order 
    When I place order for the added item
    Then I expect to see place order form
    When I fill all the details in the form
     And I click on puchase button
    Then I expect to see purchase confirmation box
     And I expect to see purchase id
     And I expect amount should be correct 
    When I close confirmation screen
    Then I expect to navigate to home screen
     And I close browser
