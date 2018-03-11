package com.livecoinalert.lca.data.util;

import android.content.Context;

import com.dreampany.framework.data.model.Category;
import com.dreampany.framework.data.util.DataUtil;
import com.dreampany.framework.data.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by air on 10/20/17.
 */

public class Util {

    public static final int WORD_RESOLVE_LIMIT = 5;

    public static final String RAW_WORD_FILE = "word.txt";
    public static final String WORD_FILE = "word.json";
    public static final String CATEGORY_FILE = "category.txt";
    public static final String TAG_FILE = "tag.json";

    public static final int IMAGE_PICK = 101;

    public static final String DIR = "data";
    public static final String FILE_DIR = DIR + File.separator + "file";
    public static final String TEMP_DIR = DIR + File.separator + "temp";

    private static final List<String> partOfSpeeches = Arrays.asList("noun", "verb", "adjective", "adverb", "pronoun", "preposition", "conjunction", "interjection");

    public static final String WORDS = "words";
    public static final String TODAY = "today";

    public static final int TodayWordLimit = 5;
    public static final int FIND_QUIZ_LIMIT = 3;
    public static final int LISTEN_QUIZ_LIMIT = 3;

    private static final int FIND_QUIZ_POINT_MULTIPLIER = 5;
    private static final int LISTEN_QUIZ_POINT_MULTIPLIER = 5;

    public static final int AD_POINTS = 500;

    public static List<String> readRawWordsFromAssets(Context context) {
        String path = DIR + File.separator + RAW_WORD_FILE;
        return FileUtil.readAssetsAsStrings(context, path);
    }

/*    public static List<Word> readWordsFromAssets(Context context) {
        String path = DIR + File.separator + WORD_FILE;
        byte[] data = FileUtil.readAssets(context, path);
        String json = new String(data);
        List<Word> words = Util.toWords(json);
        return words;
    }*/

    public static List<Category> readCategoriesFromAssets(Context context) {
        String path = DIR + File.separator + CATEGORY_FILE;
        List<String> items = FileUtil.readAssetsAsStrings(context, path);
        List<Category> categories = new ArrayList<>(items.size());
        for (String item : items) {
            String[] parts = DataUtil.splitWithSpace(item);
            categories.add(new Category(parts[0], parts[1], parts[2]));
        }
        return categories;
    }

    public static boolean isPartOfSpeech(String item) {
        return partOfSpeeches.contains(item);
    }

/*    public static List<WordTag> readTagsFromAssets(Context context) {
        String path = DIR + File.separator + TAG_FILE;
        byte[] data = FileUtil.readAssets(context, path);
        String json = new String(data);
        List<WordTag> tags = WordUtil.toTags(json);
        return tags;
    }*/

/*    public static List<Word> toWords(String value) {
        Type listType = new TypeToken<List<Word>>() {
        }.getType();
        return DataUtil.gson().fromJson(value, listType);
    }*/

/*    public static List<WordCategory> toCategories(String value) {
        Type listType = new TypeToken<List<WordCategory>>() {
        }.getType();
        return DataUtil.gson.fromJson(value, listType);
    }*/

/*    public static List<WordTag> toTags(String value) {
        Type listType = new TypeToken<List<WordTag>>() {
        }.getType();
        return DataUtil.gson.fromJson(value, listType);
    }*/

/*    public static void saveWords(Context context, List<Word> words) {
        String fileDir = PathUtil.makeExternalDir(Util.FILE_DIR);
        Storage.onInstance(context).createDirectory(fileDir);

        String json = Util.toJson(words);

        String fileUri = PathUtil.getFilePath(fileDir, WORD_FILE);
        boolean created = Storage.onInstance(context).createFile(fileUri, json.getBytes());

    }*/

/*    public static String toJson(List<Word> values) {
        Type listType = new TypeToken<List<Word>>() {
        }.getType();

        //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return DataUtil.gson().toJson(values, listType);
    }*/

/*    public static Text getText(Word word) {
        StringBuilder builder = new StringBuilder();

        if (word.hasDefinitions()) {
            for (Definition item : word.getDefinitions()) {
                DataUtil.joinString(builder, item.getText());
            }
        }

        if (word.hasExamples()) {
            for (String item : word.getExamples()) {
                DataUtil.joinString(builder, item);
            }
        }

        if (word.hasSynonyms()) {
            for (String item : word.getSynonyms()) {
                DataUtil.joinString(builder, item);
            }
        }

        if (word.hasAntonyms()) {
            for (String item : word.getAntonyms()) {
                DataUtil.joinString(builder, item);
            }
        }

        String text = builder.toString();
        return new Text(text);
    }*/


/*    public static void saveCategories(Context context, List<WordCategory> categories) {
        String fileDir = PathUtil.makeExternalDir(WordUtil.FILE_DIR);
        Storage.onInstance(context).createDirectory(fileDir);

        String json = WordUtil.toJsonFromCategory(categories);

        String fileUri = PathUtil.getFilePath(fileDir, CATEGORY_FILE);
        boolean created = Storage.onInstance(context).createFile(fileUri, json.getBytes());

    }

    public static void saveTags(Context context, List<WordTag> tags) {
        String fileDir = PathUtil.makeExternalDir(WordUtil.FILE_DIR);
        Storage.onInstance(context).createDirectory(fileDir);

        String json = WordUtil.toJsonFromTag(tags);

        String fileUri = PathUtil.getFilePath(fileDir, TAG_FILE);
        boolean created = Storage.onInstance(context).createFile(fileUri, json.getBytes());

    }

   public static String toJsonFromCategory(List<WordCategory> values) {
        Type listType = new TypeToken<List<WordCategory>>() {
        }.getType();
        return DataUtil.gson.toJson(values, listType);
    }

    public static String toJsonFromTag(List<WordTag> values) {
        Type listType = new TypeToken<List<WordTag>>() {
        }.getType();
        return DataUtil.gson.toJson(values, listType);
    }*/


/*    private void makeCategoryAndTag() {

        String[] folders = FileUtil.readFilesFromAssets(context, ASSETS_WORD_DIR);

        for (String category : folders) { // categories
            String path = FileUtil.appendForPath(ASSETS_WORD_DIR, category);
            String[] subs = FileUtil.readFilesFromAssets(context, path);

            if ("download".equals(category)) {
                continue;
            }

            for (String tag : subs) { //tags
                String filePath = FileUtil.appendForPath(path, tag);

                List<String> items = FileUtil.readAssetsAsStrings(context, filePath);

                if (items == null) {

                    continue;
                }

                for (String item : items) {
                    long wordId = DataUtil.getSha256(item.toLowerCase(Locale.ENGLISH));

                    boolean downloadItem = FrameManager.onInstance(context).hasState(wordId, WordType.WORD.value(), WordState.BUILT.value());

                    if (downloadItem) {
                        insertWordCategory(item, category);

                        String tagValue = PathUtil.removeExt(tag);
                        insertWordTag(item, tagValue);

                        LogKit.verbose("Category " + category + " tag " + tag);
                    }
                }
            }
        }

        LogKit.verbose("Category and tag completed");
    }*/

/*    public static boolean isResolved(Word word, int resolveCount) {
        if (DataUtil.toSize(word.getDefinitions()) >= resolveCount) {
            return true;
        }

        if (DataUtil.toSize(word.getExamples()) >= resolveCount) {
            return true;
        }

        if (DataUtil.toSize(word.getSynonyms()) >= resolveCount) {
            return true;
        }

        if (DataUtil.toSize(word.getAntonyms()) >= resolveCount) {
            return true;
        }

        return false;
    }*/

    public static int getFindQuizPoints(String quiz) {
        return quiz.length() * FIND_QUIZ_POINT_MULTIPLIER;
    }

    public static int getListenQuizPoints(String quiz) {
        return quiz.length() * LISTEN_QUIZ_POINT_MULTIPLIER;
    }

}
