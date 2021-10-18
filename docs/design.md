# Design

## Fundamental Abstractions

A `Player` represents a player of the game. Each player in the same game owns a unique id.

A `Turn` encapsulates information for each turn in a game, such as whose turn this is, the initial square of this player, how many steps to be taken, and the destination square. It is *clock* of the game. The notion of a `Turn` in only meaningful in the context of a `Game`

A `Game` is a play of the Monopoly Game. Note that the following information is sufficient to determine the game state:

* Players of the game
* Scores of the players
* Mapping from player to their square

The game progresses by `Turn`.

A `Square` represent a square on the game board. The determining characteristic of a `Square` is its effect on the game state when a user lands on it.

Below is a UML diagrams illustrating the above four classes.

## MVC Architecture

Users first and foremost talks to the Front Controller (FC), FC monitors the game state and invoke the corresponding controller. There are three designated controllers, one for each game state. They receive user input and update the current `Game`. FC then renders the game through the `CmdView` engine and print the game view to the user.

It is worth pointing out the FC is not the only place where the system interacts with user. The controllers can also read user input and output response when needs arise, for example, the `InGameController` can ask user whether he wants to purchase the property directly without the need to go through FC.

## System Behaviour

Sequence diagrams for all major activities:

* Start game
* Make move
* Save game

## Mics

`ThreadLocal<Game>`: `GameHolder` is thread local and can be accessed anywhere in the same thread at runtime. This reduces the explicit coupling of the essential model with controllers and views (e.g., by passing a game instance at the object construction time)

`Interactive Communication Tunnel`: The interaction of controller with users can be conducted in a virtual tunnel that bypasses the FC. This is due to the observation that some operation must be carried out in an atomic way. I/O via the FC would otherwise be inefficient and unnecessarily increases complexity. Currently, the raw `System.in` and `System.out` is used for read and write. Alternatively, an abstraction maybe used, for example, `Tunnel` that exposes the read/write interface.

`Square.onEnter(Game)`: This decision concerns where to put the logic that handles a player's entrance on a particular square (e.g., the property square, where a user can buy or must pay rent). The intuition is to model it as a method in `Square` as it is indeed a `Square`'s behaviour. Most generally, a `Sqaure`'s entrance behavior affects the game state, for example, a player is put into the jail.

Singleton `Square` subclasses: Since the squares of the Monopoly Game are pre-determined and won't change during the course of the game, any `Square` instance only needs to be present once in the memory. A `SquareFactory` is used to generate and store these `Sqaure` singletons. This increases memory efficiency. Also, since we know subclasses of `Square` will only ever be instantiated once, we can in turn make them anonymous.