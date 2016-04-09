Feature: "Televisions" page tests

Background: Opening the browser and navigating to "Televisions" home page
    Given I open "Chrome" browser 
	And I navigate to http://www.which.co.uk/reviews/televisions
	And I am not logged in 
		
#1
Scenario: Go to Best Buys page
When I click on "Best Buys" navigation element
Then I should see "Best Buys" home page
And the page title is: "Which? Best Buy televisions - Televisions reviews - Which?"

#2
Scenario: Go to Advice Guides page
When I click on "Advice Guides" navigation element
Then I should see "Advice Guides" page
And the page title is: "Guides - Televisions reviews - TV and home entertainment - Which? Tech"

#3
Scenario: Open a TV product summary page
When I click on selected item
Then I should see the Product summary page
And the page title contains: "review - Televisions reviews - Which?"

#3.1
Scenario: Navigate back to "Televisions" page
Given I am on selected TV product summary page
When I click on "Televisions" link 
Then I should see the Televisions page
And the page title is: "Televisions - TV and home entertainment - Which? Tech"

#4
Scenario: Go to Login page
When I click on "Log in" button
Then I should see "Log in" page
And the page title is: "Login"

#5
Scenario: Go to Sign Up page
When I click on "Sign Up" button
Then I should see "Sign Up" page
And the page title is: "Which? Signup"

#6
Scenario: Filter by brand
When I select a brand "Philips" from Filter side menu
Then I should see only "Philips" televisons displayed 

#7
Scenario: Filter by screen size
When I select a size "17-26"" from Filter side menu
Then I should see only "17-26" size televisons 

#8
Scenario: Filter by price
When I select from filter side menu price from "900"
And I select from filter side menu price to "1000"
Then I should see only TVs price range "900-1000" 

#9
Scenario: Go to Cars Product Reviews page
When I select "Product Reviews" button
And I select "Cars" from the extended menu
Then I should be redirected to cars landing page

#10
Scenario: Compare item price
When I click on some TV price
Then I should be redirected to "Compare Retailers" page

#11
Scenario: Sort by Price(low to high) 
When I click on "Sort by" dropdown menu
And I select "Price(low to high)" option
Then I should see TVs orderd by price from low to high

#12
Scenario: Check Sort By Dropdown 
When I click on "Sort by" dropdown menu
Then I should see following <Option>

Examples: 
	| Option |
	| Most-recently tested |
	| Highest Which? score |
	| Price (low to high) |
	| Price (high to low) |
	| Screen size (high to low) |
	| Most-recently launched |

#13
Scenario: Go to "Don't Buy" page
When I click on "Don't Buy" button from the "Filters" side menu
Then I should see a "Sign up" pop-up

