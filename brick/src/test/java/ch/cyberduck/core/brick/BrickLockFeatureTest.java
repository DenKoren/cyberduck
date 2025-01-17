package ch.cyberduck.core.brick;

/*
 * Copyright (c) 2002-2019 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import ch.cyberduck.core.DisabledConnectionCallback;
import ch.cyberduck.core.DisabledPasswordCallback;
import ch.cyberduck.core.Local;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.dav.DAVDeleteFeature;
import ch.cyberduck.core.dav.DAVFindFeature;
import ch.cyberduck.core.dav.DAVLockFeature;
import ch.cyberduck.core.dav.DAVUploadFeature;
import ch.cyberduck.core.dav.DAVWriteFeature;
import ch.cyberduck.core.exception.LockedException;
import ch.cyberduck.core.features.Delete;
import ch.cyberduck.core.http.HttpUploadFeature;
import ch.cyberduck.core.io.BandwidthThrottle;
import ch.cyberduck.core.io.DisabledStreamListener;
import ch.cyberduck.core.shared.DefaultHomeFinderService;
import ch.cyberduck.core.transfer.TransferStatus;
import ch.cyberduck.test.IntegrationTest;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.EnumSet;
import java.util.UUID;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class BrickLockFeatureTest extends AbstractBrickTest {

    @Test
    public void testLock() throws Exception {
        final TransferStatus status = new TransferStatus();
        final Local local = new Local(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
        final byte[] content = "test".getBytes(StandardCharsets.UTF_8);
        final OutputStream out = local.getOutputStream(false);
        IOUtils.write(content, out);
        out.close();
        status.setLength(content.length);
        final Path test = new Path(new DefaultHomeFinderService(session.getHost()).find(), UUID.randomUUID().toString(), EnumSet.of(Path.Type.file));
        final HttpUploadFeature upload = new DAVUploadFeature(new DAVWriteFeature(session));
        upload.upload(test, local, new BandwidthThrottle(BandwidthThrottle.UNLIMITED),
            new DisabledStreamListener(), status, new DisabledConnectionCallback());
        assertTrue(new DAVFindFeature(session).find(test));
        final String lockid = new DAVLockFeature(session).lock(test);
        assertNotNull(lockid);
        try {
            new DAVLockFeature(session).lock(test);
            fail();
        }
        catch(LockedException e) {
            // Expected
        }
        new DAVDeleteFeature(session).delete(Collections.singletonMap(test, new TransferStatus().withLockId(lockid)), new DisabledPasswordCallback(), new Delete.DisabledCallback());
    }
}
