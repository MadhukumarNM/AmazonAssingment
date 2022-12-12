Feature: Amazon home navigation

  @test
  Scenario: Television menu display when shop by TV, Appliances and Department
    When user select main menu
    And select "TV, Appliances, Electronics" in Shop By Department
    Then "Televisions" should be displayed under submenu TV, Audio & Camera
    When user select "Televisions" menu
    When select "Samsung" brand
    Then "SAMSUNG" should be displayed in results panel
    When I select dropdown value "Price: High to Low"
#    Then Records should be sorted on price high to low
    When select "2"nd priced item
    Then "About this item" should be present in next window 1

