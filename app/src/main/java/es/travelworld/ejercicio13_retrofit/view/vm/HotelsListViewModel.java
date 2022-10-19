package es.travelworld.ejercicio13_retrofit.view.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.travelworld.ejercicio13_retrofit.domain.HotelsList;
import es.travelworld.ejercicio13_retrofit.repository.HotelsListRepository;

public class HotelsListViewModel extends ViewModel {
    private final HotelsListRepository hotelsListRepository;
    private final MutableLiveData<Throwable> throwableError = new MutableLiveData<>();
    private final MutableLiveData<HotelsList> hotelsListModel = new MutableLiveData<>();

    public HotelsListViewModel(HotelsListRepository hotelsListRepository){
        this.hotelsListRepository = hotelsListRepository;
    }

    public LiveData<Throwable> getThrowable(){
        return throwableError;
    }

    public LiveData<HotelsList> getHotelsList(){
        return hotelsListModel;
    }

    public void retrieveHotelsList(){
        hotelsListRepository.getHotelsListFromServer(new HotelsListRepository.HotelsListInterface() {
            @Override
            public void onSuccess(HotelsList hotelsList) {
                hotelsListModel.setValue(hotelsList);
            }

            @Override
            public void onError(Throwable throwable) {
                throwableError.setValue(throwable);
            }
        });
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final HotelsListRepository hotelsListRepository;

        public Factory(HotelsListRepository hotelsListRepository){
            this.hotelsListRepository = hotelsListRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new HotelsListViewModel(hotelsListRepository);
        }
    }
}
