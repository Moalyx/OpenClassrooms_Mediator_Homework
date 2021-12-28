package fr.delcey.mediator.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> nameMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> ageMutableLiveData = new MutableLiveData<>(0);

    private final MediatorLiveData<String> messageMediatorLiveData = new MediatorLiveData<>();

    public MainViewModel() {
        messageMediatorLiveData.addSource(nameMutableLiveData, new Observer<String>() {
            @Override
            public void onChanged(String newName) {
                combine(newName, ageMutableLiveData.getValue());
            }
        });

        messageMediatorLiveData.addSource(ageMutableLiveData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer newAge) {
                combine(nameMutableLiveData.getValue(), newAge);
            }
        });
    }

    private void combine(String newName, Integer newAge) {
        messageMediatorLiveData.setValue("Salut " + newName + ", tu as " + newAge + " ans.");
    }

    public LiveData<String> getMessageLiveData() {
        return messageMediatorLiveData;
    }

    public void onNameChanged(String name) {
        nameMutableLiveData.setValue(name);
    }

    public void onIncreaseButtonClicked() {
        ageMutableLiveData.setValue(ageMutableLiveData.getValue() + 1);
    }

    public void onSexChanged(boolean isAWoman) {
        // TODO 
    }
}