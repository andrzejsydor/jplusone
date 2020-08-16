/*
 * Copyright (c) 2020 Adam Gaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grexdev.jplusone.core.proxy.datasource;

import com.grexdev.jplusone.core.proxy.StateListener;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
class StatementProxy implements Statement {

    @Delegate(excludes = StatementOverwrite.class)
    private final Statement delegate;

    private final StateListener stateListener;

    private final List<String> batchSql = new ArrayList<>();

    @Override
    public boolean execute(String sql) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.execute(sql);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.execute(sql, columnNames);
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeQuery(sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeUpdate(sql);
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeUpdate(sql, columnNames);
    }

    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeLargeUpdate(sql);
    }

    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeLargeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeLargeUpdate(sql, columnIndexes);
    }

    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        stateListener.statementExecuted(sql);
        return delegate.executeLargeUpdate(sql, columnNames);
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        batchSql.add(sql);
        delegate.addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        batchSql.clear();
        delegate.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        batchSql.forEach(stateListener::statementExecuted);
        return delegate.executeBatch();
    }

    @Override
    public long[] executeLargeBatch() throws SQLException {
        batchSql.forEach(stateListener::statementExecuted);
        return delegate.executeLargeBatch();
    }

    private interface StatementOverwrite {

        ResultSet executeQuery(String sql) throws SQLException;

        boolean execute(String sql) throws SQLException;

        boolean execute(String sql, int autoGeneratedKeys) throws SQLException;

        boolean execute(String sql, int columnIndexes[]) throws SQLException;

        boolean execute(String sql, String columnNames[]) throws SQLException;

        int executeUpdate(String sql) throws SQLException;

        int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException;

        int executeUpdate(String sql, int columnIndexes[]) throws SQLException;

        int executeUpdate(String sql, String columnNames[]) throws SQLException;

        long executeLargeUpdate(String sql) throws SQLException;

        long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException;

        long executeLargeUpdate(String sql, int columnIndexes[]) throws SQLException;

        long executeLargeUpdate(String sql, String columnNames[]) throws SQLException;

        void addBatch(String sql) throws SQLException;

        void clearBatch() throws SQLException;

        int[] executeBatch() throws SQLException;

        long[] executeLargeBatch() throws SQLException;

    }
}