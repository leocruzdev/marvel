package com.dacruzl2.marvel.character.list

import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import com.dacruzl2.marvel.character.list.domain.model.DomainImage
import com.dacruzl2.marvel.character.list.domain.usecase.GetCharacters
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CharactersUsecase {

    private val repository = mockk<CharacterRepository>()
    private val getCharacters = GetCharacters(repository)

    @Test
    fun getCharacters_return_list_when_success() = runBlocking {
        // GIVEN
        val listCharacters = listOf(
            DomainCharacter(
                name = "Spider", id = 1L, thumbnail = DomainImage(path = "https://", extension = ".jpg")
            ),
            DomainCharacter(
                name = "Iron Man", id = 1L, thumbnail = DomainImage(path = "https//", extension = ".jpg")
            )
        )

        coEvery { repository.getCharacters() } returns flowOf(listCharacters)

        // WHEN
        val result = getCharacters().single()

        // THEN
        assertEquals(result.size, listCharacters.size)
    }

    @Test
    fun getCharacters_return_list_when_image_is_not_available() = runBlocking {
        // GIVEN
        val listCharacters = listOf(
            DomainCharacter(
                name = "Spider", id = 1L, thumbnail = DomainImage(path = "image_not_available", extension = ".jpg")
            )
        ).filter { list -> list.thumbnail?.invoke()?.contains("image_not_available")?.not() == true }

        coEvery { repository.getCharacters() } returns flowOf(emptyList())

        // WHEN
        val result = getCharacters().single()

        // THEN
        assertEquals(result, listCharacters)
    }
}
