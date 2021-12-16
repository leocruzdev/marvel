package com.dacruzl2.marvel.character.list.di

import com.dacruzl2.marvel.character.list.data.CharacterRepositoryImpl
import com.dacruzl2.marvel.character.list.data.CharactersService
import com.dacruzl2.marvel.character.list.data.mapper.BaseCharacterAttrsMapper
import com.dacruzl2.marvel.character.list.data.mapper.ImageMapper
import com.dacruzl2.marvel.character.list.data.mapper.ItemMapper
import com.dacruzl2.marvel.character.list.data.mapper.UrlMapper
import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import com.dacruzl2.marvel.character.list.domain.usecase.GetCharacters
import com.dacruzl2.marvel.character.list.presentation.CharactersListViewModel
import com.dacruzl2.marvel.character.list.presentation.mapper.ViewImageMapper
import com.dacruzl2.marvel.networking.OkhttpBuilder
import com.dacruzl2.marvel.networking.RetrofitBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.dacruzl2.marvel.character.list.data.mapper.CharacterMapper as CharacterMapper
import com.dacruzl2.marvel.character.list.presentation.mapper.CharacterMapper as CharacterViewMapper

val charactersModule by lazy {
    module {

        // Data
        single {
            RetrofitBuilder<CharactersService>(
                apiURL = "http://gateway.marvel.com/v1/public/",
                oKhttpClient = OkhttpBuilder()
            )
        }

        single<CharacterRepository> {
            CharacterRepositoryImpl(
                service = get(),
                mapper = get()
            )
        }

        // Data to domain mappers
        factory { CharacterMapper(urlMapper = get(), imageMapper = get(), baseAttrsMapper = get()) }
        factory { BaseCharacterAttrsMapper(itemMapper = get()) }
        factory { ItemMapper() }
        factory { ImageMapper() }
        factory { UrlMapper() }

        // Presentation

        // Domain to view mappers
        factory { CharacterViewMapper(viewImageMapper = get()) }
        factory { ViewImageMapper() }

        viewModel {
            val useCaseGetCharacters = GetCharacters(repository = get())
            CharactersListViewModel(usecase = useCaseGetCharacters, characterMapper = get())
        }
    }
}