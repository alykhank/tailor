# Contributing

* [Getting Started](#getting-started)
* [Developing](#developing)
* [Committing Changes](#committing-changes)
* [Opening an Issue](#opening-an-issue)
* [Creating a Pull Request (PR)](#creating-a-pull-request-pr)
* [Triaging Issues](#triaging-issues)

## Getting Started

* Set up dependencies:
```bash
script/bootstrap
```

* Run tests locally:
```bash
script/test
```

* Please set up [EditorConfig](http://editorconfig.org) for your editor to automatically comply with the `.editorconfig`

## Developing

* The ANTLR-executable version of the Swift grammar (based on [The Swift Programming Language: Summary of the Grammar](https://developer.apple.com/library/prerelease/ios/documentation/Swift/Conceptual/Swift_Programming_Language/zzSummaryOfTheGrammar.html)) is located at `grammar/Swift.g4`
* The lexer, parser, and listener generated via ANTLR from the grammar are located in `tailor/swift/`
* If modifications to the [ANTLR](http://www.antlr.org) grammar are necessary, regenerate the lexer/parser/listener:
```bash
script/antlr
```

* Ensure that all new code is tested via unit tests (`tailor/tests/`) and/or functional tests (`functional_tests/`)

## Committing Changes

* Do not commit to `master`, your changes will be reverted. Always create a new branch and submit your changes as a pull request
* Please follow Tim Pope's Git commit message guidelines in [A Note About Git Commit Messages](http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html)
* Specifically, ensure that the first line of your commit message is brief (50 chars or less) and "Capitalized with no terminating period", then leave a blank line and provide more detailed explanatory text if necessary in paragraph or bulleted form with "-" or "*"
* Attempt to keep the amount of modified code per commit to a reasonable size and refer to any relevant issues with their ID, using the verbs fix/close/resolve to automatically close issues once the commit has been merged into `master` (e.g. "Fix #4: Add .travis.yml to test against Python 3.4")

## Opening an Issue

* Create issues for features, enhancements, bugs, or questions and always attempt to specify a label and milestone if possible
* If you have discussed responsibility for an issue with a contributor then set them as the assignee, otherwise leave it unassigned and the issue will be triaged to any contributor
* Use a descriptive phrase for the issue name, capitalized with no terminating period, e.g. "Set up continuous integration via Travis"

## Creating a Pull Request (PR)

### Naming a Branch

* Create feature branches off of `master` with the naming convention: `aa-feature-description`, where `aa` are your initials and `feature-description` is a descriptive phrase that describes your feature
* Ensure branch names always use lower case letters, with hyphens to separate words, never underscores

### Submitting a PR

* Once your feature is complete, push your branch to GitHub and open a PR
* PRs should be based on your branch name with your initials removed and the name capitalized, i.e. `aa-feature-description` will become "Feature description"
* If your PR shadows an issue, match the label and milestone to that issue
* Assign a primary reviewer when opening your PR, and have at least 2 people endorse your request
* If you are waiting for a second reviewer, you may reassign the PR to another contributor

### Closing a PR

* Once you have received two :+1:s and the [Travis CI](https://magnum.travis-ci.com/alykhank/tailor) build has passed, you are responsible for merging your branch into `master` and subsequently deleting it from the remote
* When merging your branch, refer to the issue at the beginning of the commit description (e.g. "Fix #4: Integrate Travis CI")

## Triaging Issues

Issues will be triaged during scrum from the Backlog and moved into Ready/In Progress using [Waffle](https://waffle.io/alykhank/tailor)
