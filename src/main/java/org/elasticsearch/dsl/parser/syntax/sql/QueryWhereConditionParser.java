package org.elasticsearch.dsl.parser.syntax.sql;

import org.elasticsearch.dsl.bean.ElasticDslContext;
import org.elasticsearch.dsl.parser.listener.ParseActionListener;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.sql.ElasticSqlSelectQueryBlock;

public class QueryWhereConditionParser extends AbstractQueryConditionParser {

    public QueryWhereConditionParser(ParseActionListener parseActionListener) {
        super(parseActionListener);
    }

    @Override
    public void parse(ElasticDslContext dslContext) {
        ElasticSqlSelectQueryBlock queryBlock = (ElasticSqlSelectQueryBlock) dslContext.getQueryExpr().getSubQuery().getQuery();

        if (queryBlock.getWhere() != null) {
            String queryAs = dslContext.getParseResult().getQueryAs();

            BoolQueryBuilder whereQuery = parseQueryConditionExpr(queryBlock.getWhere(), queryAs, dslContext.getSqlArgs());

            dslContext.getParseResult().setWhereCondition(whereQuery);
        }
    }
}
