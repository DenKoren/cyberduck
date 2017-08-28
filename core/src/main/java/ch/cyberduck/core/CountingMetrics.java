package ch.cyberduck.core;

/*
 * Copyright (c) 2002-2017 iterate GmbH. All rights reserved.
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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CountingMetrics implements Metrics {

    private final Map<Object, AtomicLong> metrics = new ConcurrentHashMap<>();

    @Override
    public <T> void increment(final T key) {
        if(!metrics.containsKey(key)) {
            metrics.put(key, new AtomicLong());
        }
        metrics.get(key).getAndIncrement();
    }

    @Override
    public <T> long get(final T key) {
        return metrics.containsKey(key) ? metrics.get(key).longValue() : 0;
    }
}
