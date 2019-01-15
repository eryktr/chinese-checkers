# chinese-checkers
A client-server implementation of the popular chinese checkers

## Client
The client was created using JavaFX. It allows the user to fill in the information about the desired endpoint,
browse through the list of games hosted on the server, join to an existing one as well as create their own game room.

## Server
The server was implemented using TCP sockets.
The server is multithreaded, allowing multiple games taking place in parallel.

The server also supports a simple AI - you might choose the desired number of computer players
that will play against you.

To implement the features, string-based communication protocol was used.
