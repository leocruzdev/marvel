package com.dacruzl2.marvel.character.list.domain.usecase

import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import com.dacruzl2.marvel.character.list.domain.model.DomainImage
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class CharactersUsecase {

    private lateinit var repository: CharacterRepository
    private lateinit var getCharacters: GetCharacters

    @BeforeEach
    fun setUp() {
        repository = mockk()
        getCharacters = GetCharacters(repository = repository)
    }

    @Test
    fun getCharacters_return_list_when_image_is_available() = runBlocking {
        // GIVEN
        val character1 = DomainCharacter(
            name = "Spider", id = 1L, thumbnail = DomainImage(path = "https://", extension = ".jpg")
        )
        val character2 = DomainCharacter(
            name = "Iron Man", id = 1L, thumbnail = DomainImage(path = "https//", extension = ".jpg")
        )

        val listCharacters = listOf(character1, character2)

        coEvery { repository.getCharacters() } returns flowOf(listCharacters)

        // WHEN
        val result = getCharacters().single().filter { characterItem ->
            characterItem.filterContainsImageNotAvailable()
        }
        val filtredList = listCharacters.filter { characterItem ->
            characterItem.filterContainsImageNotAvailable()
        }

        // THEN
        assertEquals(result.size, filtredList.size)
    }

    @Test
    fun getCharacters_return_list_when_image_is_not_available() = runBlocking {
        // GIVEN
        val character1 = DomainCharacter(
            name = "Spider", id = 1L, thumbnail = DomainImage(path = "https://", extension = ".jpg")
        )

        val character2 = DomainCharacter(
            name = "Spider", id = 1L, thumbnail = DomainImage(path = "https://image_not_available", extension = ".jpg")
        )

        val listCharacters = listOf(character1, character2)

        coEvery { repository.getCharacters() } returns flowOf(listCharacters)

        // WHEN
        val result = getCharacters().single()
        val filtredList = listCharacters.filter { characterItem ->
            characterItem.filterNotContainsImageNotAvailable()
        }

        // THEN
        assertEquals(result.size, filtredList.size)
    }

    private fun DomainCharacter.filterNotContainsImageNotAvailable(): Boolean {
        return this.thumbnail?.invoke()?.contains("image_not_available")?.not() == false
    }

    private fun DomainCharacter.filterContainsImageNotAvailable(): Boolean {
        return this.thumbnail?.invoke()?.contains("image_not_available")?.not() == true
    }

    @AfterEach
    fun tearDown() {
    }
}