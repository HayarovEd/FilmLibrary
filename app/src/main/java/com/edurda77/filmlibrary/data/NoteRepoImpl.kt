package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.domain.NoteRepo

class NoteRepoImpl: NoteRepo {
    private val cacheNotes: MutableList<NoteMovie> = mutableListOf()
    override fun add(note: NoteMovie) {
        cacheNotes.add(note)
    }

    override fun getNots(): List<NoteMovie> {
        return ArrayList<NoteMovie>(cacheNotes)
    }

    override fun delete(id: Int) {
        val indexOfDelete = cacheNotes.indexOfFirst {
            it.idNote == id
        }
        cacheNotes.removeAt(indexOfDelete)

    }

    override fun update(id: Int, note: NoteMovie) {
        val indexOfDelete = cacheNotes.indexOfFirst {
            it.idNote == id
        }
        cacheNotes.removeAt(indexOfDelete)
        cacheNotes.add(indexOfDelete, note)
    }

    override fun clearNots() {
        cacheNotes.clear()


    }

}