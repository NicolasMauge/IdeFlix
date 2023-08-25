package org.epita.application.mediaDataBase.mediaDataBase;

import org.epita.domaine.mediaDataBase.MediaAudioVisuelDataBase;

import java.util.List;

public interface MediaDataBaseService {

    List<MediaAudioVisuelDataBase> searchSuggestedMediasSelonPreferences(String email, int page);
}
