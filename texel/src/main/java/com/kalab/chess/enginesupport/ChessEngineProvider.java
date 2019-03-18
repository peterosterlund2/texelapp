/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kalab.chess.enginesupport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;

public abstract class ChessEngineProvider extends ContentProvider {

    private static final String MIME_TYPE = "application/x-chess-engine";
    private static final String UNSUPPORTED = "Not supported by this provider";

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode)
            throws FileNotFoundException {
        String fileName = uri.getLastPathSegment();
        if (fileName == null)
            throw new FileNotFoundException();
        AssetManager manager = getContext().getAssets();
        for (String abi : Build.SUPPORTED_ABIS) {
            try {
                return manager.openFd(abi + "/" + fileName);
            } catch (IOException e) {
            }
        }
        throw new FileNotFoundException();
    }

    public ParcelFileDescriptor openLibFile(File f)
            throws FileNotFoundException {
        return ParcelFileDescriptor
                .open(f, ParcelFileDescriptor.MODE_READ_ONLY);
    }

    @Override
    public String getType(Uri uri) {
        return MIME_TYPE;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        throw new UnsupportedOperationException(UNSUPPORTED);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException(UNSUPPORTED);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException(UNSUPPORTED);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        throw new UnsupportedOperationException(UNSUPPORTED);
    }
}
