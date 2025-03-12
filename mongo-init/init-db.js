db = db.getSiblingDB('gacha');

// Seed some users for Auth API
db.users.insertMany([
    { username: "player1", password: "test" },
    { username: "player2", password: "test" }
]);

// Seed a sample player for Player API
db.players.insertMany([
    {
        _id: "1",
        username: "player1",
        level: 1,
        experience: 0,
        maxMonsters: 10
    }
]);
