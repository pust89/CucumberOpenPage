Feature: MatrixBoard test
  @Test
  Scenario: Negative Login Admin Account Test to MatrixBoard
    Given user navigates to 'http://at.pflb.ru/matrixboard2/'
    When he fill login field with 'admin'
    And he fill password field with 'badPass'
    And he click submit button
    Then he should see error message



  @Test
  Scenario: Positive Login Admin Account Test to MatrixBoard
    Given user navigates to 'http://at.pflb.ru/matrixboard2/'
    When he fill login field with 'admin'
    And he fill password field with 'admin'
    And he click submit button
    Then he should see 'admin' in profile box
    And user logging out

  @Test
  Scenario: Admin Rights Test to MatrixBoard
    Given user navigates to 'http://at.pflb.ru/matrixboard2/'
    When he fill login field with 'admin'
    And he fill password field with 'admin'
    And he click submit button
    Then he should click on add person button
    Then he should see add person window
    And he close add person window
    And admin logging out

  @Test
  Scenario: Add Person Test to MatrixBoard
    Given user navigates to 'http://at.pflb.ru/matrixboard2/'
    When he fill login field with 'admin'
    And he fill password field with 'admin'
    And he click submit button
    Then he should click on add person button
    And he should feel laste name field 'Sername73'
    And he should feel first name field 'Name48'
    Then press add new person button
    And admin must see 'Sername' and 'Name' at persons cards
    And admin logging out







