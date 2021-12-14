package com.dacruzl2.marvel.character.list.di

import com.dacruzl2.marvel.character.list.data.CharacterRepositoryImpl
import com.dacruzl2.marvel.character.list.data.CharactersService
import com.dacruzl2.marvel.character.list.data.mapper.BaseCharacterAttrsMapper
import com.dacruzl2.marvel.character.list.data.mapper.CharacterMapper
import com.dacruzl2.marvel.character.list.data.mapper.ImageMapper
import com.dacruzl2.marvel.character.list.data.mapper.ItemMapper
import com.dacruzl2.marvel.character.list.data.mapper.UrlMapper
import com.dacruzl2.marvel.character.list.domain.CharacterRepository
import com.dacruzl2.marvel.character.list.domain.usecase.GetCharacters
import com.dacruzl2.marvel.character.list.presentation.CharactersListViewModel
import com.dacruzl2.marvel.networking.OkhttpBuilder
import com.dacruzl2.marvel.networking.RetrofitBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule by lazy {
    module {

        single {
            RetrofitBuilder<CharactersService>(
                apiURL = "http://gateway.marvel.com/v1/public/",
                oKhttpClient = OkhttpBuilder()
            )
        }

        // Repository
        single<CharacterRepository> {
            CharacterRepositoryImpl(
                service = get(),
                mapper = get()
            )
        }

        // Mappers
        factory { CharacterMapper(urlMapper = get(), imageMapper = get(), baseAttrsMapper = get()) }
        factory { BaseCharacterAttrsMapper(itemMapper = get()) }
        factory { ItemMapper() }
        factory { ImageMapper() }
        factory { UrlMapper() }

        viewModel {
            val useCaseGetCharacters = GetCharacters(repository = get())
            CharactersListViewModel(usecase = useCaseGetCharacters)
        }
    }
}