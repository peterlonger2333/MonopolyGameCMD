# API Changelog

## [0.0.1] - 2021.11.12

### Added

- The `Dices` class to wrap the activities related to dice drawing. In essence, it's just a random number generator with a customized distribution. The `makeDraw` method wraps the interaction with user. This class is added due to the observation that the usage of dice could be sparse, so it's better to keep this function at a single place. 

- `Utils` class keeps utility methods, for example, read user input.

### Changed

- A new method is added to `JailSquare` - `onPrisonerTurn(Game)`. The original `onEnter(Game)` is only kept as a placeholder. `onPrisonerTurn` is called by `InGameController` once it detects that the current player is in prison. The current turn is then fully delegated to `onPrisonerTurn`. The rationale behind this change is that `JailSquare` in fact alters the whole game flow. Tests are added correspondingly.

### Removed