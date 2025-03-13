db = db.getSiblingDB('gacha');

// Seed users for authentification-api
db.users.insertMany([
    { username: "player1", password: "test" },
    { username: "player2", password: "test" }
]);

// Seed a sample player for player-api
db.players.insertMany([
    { _id: "1", username: "player1", level: 0, experience: 50, monsterIds: [], maxMonsters: 10 }
]);

