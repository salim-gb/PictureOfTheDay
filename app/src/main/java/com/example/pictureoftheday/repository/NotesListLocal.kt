package com.example.pictureoftheday.repository

import com.example.pictureoftheday.model.NoteBig
import com.example.pictureoftheday.model.NoteSmall
import com.example.pictureoftheday.model.ListItem

fun dataList(): List<ListItem> {
    return listOf(
        NoteSmall(1, "Note 1", "Remind Note 1", "12-01-2022"),
        NoteSmall(2, "Note 2", "Remind Note 2", "12-01-2022"),
        NoteSmall(3, "Note 3", "Remind Note 3", "12-01-2022"),
        NoteBig(
            6,
            "Note 500",
            "Remind Note Big 500",
            "Remind Note Big Description Two",
            "12-01-2022"
        ),
        NoteSmall(4, "Note 4", "Remind Note 4", "12-01-2022"),
        NoteSmall(5, "Note 5", "Remind Note 5", "12-01-2022"),
        NoteSmall(6, "Note 6", "Remind Note 6", "12-01-2022"),
        NoteBig(
            6,
            "Note 600",
            "Remind Note Big 600",
            "Remind Note Big Description Two",
            "12-01-2022"
        ),
        NoteSmall(7, "Note 7", "Remind Note 7", "12-01-2022"),
        NoteSmall(8, "Note 8", "Remind Note 8", "12-01-2022"),
        NoteSmall(9, "Note 9", "Remind Note 9", "12-01-2022"),
        NoteSmall(10, "Note 10", "Remind Note 10", "12-01-2022"),
        NoteBig(
            6,
            "Note 700",
            "Remind Note Big 700",
            "Remind Note Big Description Two",
            "12-01-2022"
        ),
        NoteSmall(11, "Note 11", "Remind Note 11", "12-01-2022"),
    )
}