package hr.foreal.showsmarkosoljic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.network.IsaApi
import hr.foreal.showsmarkosoljic.network.RetrofitClient
import hr.foreal.showsmarkosoljic.networkModels.RegisterUserResponse
import hr.foreal.showsmarkosoljic.networkModels.TokenData
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRepositoryImpl : TvShowRepository {

    private var tvShowList: ArrayList<TvShow> = arrayListOf()
    private var tokenData: MutableLiveData<TokenData> = MutableLiveData()
    private var api: IsaApi? = null
    var registerResponse: MutableLiveData<RegisterUserResponse> = MutableLiveData()


    init {
        api = RetrofitClient.retrofitInstance?.create(IsaApi::class.java)

    }


    private val theOffice = TvShow(
        1,
        R.drawable.office_list_poster,
        R.drawable.the_affair_poster,
        "The Office",
        2005,
        arrayListOf(),
        "A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium."
    )
    private val bigBang = TvShow(
        2,
        R.drawable.bang_list_poster, R.drawable.bang_details_poster,
        "The Big Bang Theory",
        2007,
        arrayListOf(),
        "A woman who moves into an apartment across the hall from two brilliant but socially awkward physicists shows them how little they know about life outside of the laboratory."
    )
    private val janeTheVirgin = TvShow(
        7,
        R.drawable.jane_list_poster, R.drawable.jane_details_poster,
        "Jane the Virgin",
        2019,
        arrayListOf(),
        "A young, devout Catholic woman discovers that she was accidentally artificially inseminated."
    )

    private val house = TvShow(
        3,
        R.drawable.house_list_poster, R.drawable.house_details_poster,
        "House M.D.",
        2019,
        arrayListOf(),
        "An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits."
    )
    private val sherlock = TvShow(
        4,
        R.drawable.sherlock_list_poster, R.drawable.sherlock_details_poster,
        "Sherlock",
        2010,
        arrayListOf(),
        "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London."
    )

    private val itsAllwaysSunny = TvShow(
        5,
        R.drawable.sunny_list_poster, R.drawable.sunny_details_poster,
        "Its allways sunny in Philadelphia",
        2005,
        arrayListOf(),
        "Five friends with big egos and slightly arrogant attitudes are the proprietors of an Irish pub in Philadelphia."
    )


    override fun getAllShows(): ArrayList<TvShow> {
        tvShowList = arrayListOf(house, theOffice, janeTheVirgin, itsAllwaysSunny, sherlock, bigBang)
        return tvShowList
    }

    override fun addEpisode(showId: Int, episode: Episode) {
        for (ep in tvShowList) {
            if (ep.id == showId) {
                ep.listOfEpisodes.add(episode)
            }
        }
    }

    override fun getEpisodes(showId: Int): ArrayList<Episode> {
        val showEpisodes = arrayListOf<Episode>()
        for (show in tvShowList) {
            if (show.id == showId) {
                showEpisodes.addAll(show.listOfEpisodes)
            }
        }
        return showEpisodes
    }

    override fun createAccount(loginModel: UserLoginModel) {
        api?.registerUser(loginModel)?.enqueue(object : Callback<RegisterUserResponse> {
            override fun onResponse(call: Call<RegisterUserResponse>, response: Response<RegisterUserResponse>) {
                if (call.isExecuted && response.isSuccessful && response.body() != null) {
                    registerResponse.value = response.body()
                    login(loginModel)
                    Log.d("marko", "register response recived")
                }
            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                Log.d("marko", "an error happend on register : ${t.localizedMessage}")
                registerResponse.value = null
            }
        })
    }

    override fun login(loginModel: UserLoginModel) {
        api?.loginUser(loginModel)?.enqueue(object : Callback<TokenData> {
            override fun onResponse(call: Call<TokenData>, response: Response<TokenData>) {
                if (call.isExecuted && response.isSuccessful && response.body() != null) {
                    tokenData.value = response.body()
                    Log.d("marko", "token data recived ${response.body()?.tokenData}")
                }
            }


            override fun onFailure(call: Call<TokenData>, t: Throwable) {
                Log.d("marko", "an error happend on register : ${t.localizedMessage}")
                tokenData.value = null
            }
        })
    }

    override fun observeRegisterUserResponse(): LiveData<RegisterUserResponse> {
        return registerResponse
    }

    override fun observeLoginResponseData(): LiveData<TokenData> {
        return tokenData
    }
}