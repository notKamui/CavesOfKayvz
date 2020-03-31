package com.example.cavesofkayvz.model

import kotlin.random.Random

class Floor {
    private val rooms: Array<Room?> = arrayOfNulls(floorSize * floorSize)
    val enemiesPool = listOf(Bat("", 3, 100, 20, 30), Skeleton("", 3, 300, 30, 10))

    companion object {
        const val floorSize: Int = 7
        const val minRoomNumber: Int = 5
        const val maxRoomNumber: Int = 8
        const val enemyRate: Double = 0.75  //between 0 and 1
    }

    fun createFloor(player: Player) {
        rooms[rooms.size / 2] = Safe(player)
        var room: Room
        for (i in 0..Random.nextInt(minRoomNumber, maxRoomNumber)) {
            var placed = false
            while (!placed) {
                var slot = Random.nextInt(rooms.size - 1)
                if (canBePlaced(slot)) {
                    room = if (Random.nextDouble() < enemyRate) {
                        Battle(mutableListOf(enemiesPool.random(), enemiesPool.random()))
                    } else {
                        Treasure()
                    }
                    var newNeighbors = getAdjRooms(slot)
                    for (neighbor in newNeighbors) {
                        neighbor.neighbors.add(room)
                    }
                    room.neighbors.addAll(newNeighbors)
                    rooms[slot] = room
                    placed = true
                }
            }
        }
    }

    private fun canBePlaced(i: Int): Boolean {
        return rooms[i] == null && getAdjRooms(i).isNotEmpty()

    }

    private fun getAdjRooms(i: Int): List<Room> {
        return listOfNotNull(
            if (i % floorSize != 0) rooms[i - 1] else null,
            if (i % floorSize != floorSize - 1) rooms[i + 1] else null,
            rooms.getOrNull(i - floorSize),
            rooms.getOrNull(i + floorSize)
        )
    }

}
