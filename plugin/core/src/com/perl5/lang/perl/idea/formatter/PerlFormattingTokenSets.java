/*
 * Copyright 2015-2020 Alexandr Evstigneev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perl5.lang.perl.idea.formatter;

import com.intellij.psi.tree.TokenSet;
import com.perl5.lang.perl.lexer.PerlElementTypes;
import com.perl5.lang.perl.lexer.PerlTokenSets;
import com.perl5.lang.perl.psi.stubs.PerlStubElementTypes;

import static com.perl5.lang.perl.lexer.PerlTokenSets.*;


public interface PerlFormattingTokenSets extends PerlElementTypes {
  TokenSet FOR_OR_FOREACH = TokenSet.create(
    RESERVED_FOR, RESERVED_FOREACH
  );

  TokenSet CONDITION_LIKE_ELEMENTS = TokenSet.create(
    CONDITION_EXPR,
    FOR_COMPOUND
  );

  TokenSet SUB_DEFINITIONS_TOKENSET = TokenSet.create(
    METHOD_DEFINITION,
    FUNC_DEFINITION,
    SUB_EXPR,
    SUB_DEFINITION,
    SUB_DECLARATION,
    FUN_EXPR,
    METHOD_EXPR
  );

  TokenSet INVOCANTS_TOKENSET = TokenSet.create(
    METHOD_SIGNATURE_INVOCANT, AROUND_SIGNATURE_INVOCANTS
  );

  TokenSet SUB_OR_MODIFIER_DEFINITIONS_TOKENSET = TokenSet.orSet(
    SUB_DEFINITIONS_TOKENSET, MODIFIER_DECLARATIONS_TOKENSET
  );

  TokenSet SECONDARY_COMPOUND_TOKENSET = TokenSet.create(
    CONTINUE_BLOCK,
    RESERVED_ELSE,
    RESERVED_ELSIF,
    DEFAULT_COMPOUND,
    CATCH_EXPR,
    FINALLY_EXPR,
    EXCEPT_EXPR,
    OTHERWISE_EXPR,
    CONTINUATION_EXPR
  );

  /**
   * @deprecated looks like {@link PerlTokenSets#COMPOUND_STATEMENTS}
   */
  @Deprecated
  TokenSet COMPOUND_STATEMENTS_TOKENSET = TokenSet.create(
    IF_COMPOUND,
    UNLESS_COMPOUND,
    UNTIL_COMPOUND,
    WHILE_COMPOUND,
    GIVEN_COMPOUND,
    WHEN_COMPOUND,
    TRYCATCH_COMPOUND,
    FOR_COMPOUND,
    FOREACH_COMPOUND
  );


  // fixme doesn't duplicate BlockOwner interface?
  TokenSet BLOCK_CONTAINERS_TOKENSET = TokenSet.orSet(
    COMPOUND_STATEMENTS_TOKENSET,
    TokenSet.create(
      CONDITIONAL_BLOCK,
      UNCONDITIONAL_BLOCK,
      CONTINUE_BLOCK,
      DEFAULT_COMPOUND,

      TRY_EXPR,
      CATCH_EXPR,
      FINALLY_EXPR,
      EXCEPT_EXPR,
      OTHERWISE_EXPR,
      CONTINUATION_EXPR
    ));

  TokenSet STATEMENT_MODIFIERS = TokenSet.create(
    IF_STATEMENT_MODIFIER,
    UNLESS_STATEMENT_MODIFIER,
    WHILE_STATEMENT_MODIFIER,
    UNTIL_STATEMENT_MODIFIER,
    FOR_STATEMENT_MODIFIER,
    WHEN_STATEMENT_MODIFIER
  );

  TokenSet RESERVED_CONDITIONAL_BRANCH_KEYWORDS = TokenSet.create(
    RESERVED_IF,
    RESERVED_UNLESS,
    RESERVED_ELSIF
  );

  TokenSet RESERVED_COMPOUND_CONDITIONAL = TokenSet.create(
    RESERVED_GIVEN,
    RESERVED_WHILE,
    RESERVED_UNTIL,
    RESERVED_WHEN,
    RESERVED_WHILE,

    // these are redundant here, but used in statement modifiers
    RESERVED_FOR,
    RESERVED_FOREACH
  );

  TokenSet RESERVED_TERMS_BLOCKS = TokenSet.create(
    RESERVED_DO,
    RESERVED_EVAL,
    RESERVED_SUB,
    RESERVED_MAP,
    RESERVED_GREP,
    RESERVED_SORT
  );


  TokenSet OPERATORS_ADDITIVE = TokenSet.create(
    OPERATOR_PLUS,
    OPERATOR_MINUS
  );

  TokenSet OPERATORS_MULTIPLICATIVE = TokenSet.create(
    OPERATOR_MUL,
    OPERATOR_DIV,
    OPERATOR_POW,
    OPERATOR_MOD
  );

  TokenSet OPERATORS_SHIFT = TokenSet.create(
    OPERATOR_SHIFT_LEFT,
    OPERATOR_SHIFT_RIGHT
  );

  TokenSet OPERATORS_RANGE = TokenSet.create(
    OPERATOR_FLIP_FLOP,
    OPERATOR_HELLIP
  );

  TokenSet OPERATORS_UNARY = TokenSet.create(
    OPERATOR_PLUS_PLUS,
    OPERATOR_NOT,
    OPERATOR_MINUS_MINUS,
    OPERATOR_BITWISE_NOT
  );

  TokenSet OPERATORS_LOGICAL = TokenSet.create(
    OPERATOR_AND,
    OPERATOR_OR,
    OPERATOR_OR_DEFINED
  );

  TokenSet OPERATORS_EQUALITY = TokenSet.create(
    OPERATOR_EQ_NUMERIC,
    OPERATOR_NE_NUMERIC,
    OPERATOR_RE,
    OPERATOR_NOT_RE
  );

  TokenSet OPERATORS_RELATIONAL = TokenSet.create(
    OPERATOR_GE_NUMERIC,
    OPERATOR_LE_NUMERIC,
    OPERATOR_SMARTMATCH,
    OPERATOR_GT_NUMERIC,
    OPERATOR_LT_NUMERIC,
    OPERATOR_CMP_NUMERIC
  );

  TokenSet OPERATORS_ALPHABETICAL = TokenSet.create(
    OPERATOR_GE_STR,
    OPERATOR_LE_STR,
    OPERATOR_GT_STR,
    OPERATOR_LT_STR,
    OPERATOR_CMP_STR,
    OPERATOR_EQ_STR,
    OPERATOR_NE_STR,
    OPERATOR_OR_LP,
    OPERATOR_AND_LP,
    OPERATOR_NOT_LP,
    OPERATOR_XOR_LP,
    OPERATOR_ISA
  );

  TokenSet BINARY_EXPRESSIONS = TokenSet.create(
    LP_OR_XOR_EXPR,
    LP_AND_EXPR,
    FLIPFLOP_EXPR,
    OR_EXPR,
    AND_EXPR,
    BITWISE_OR_XOR_EXPR,
    BITWISE_AND_EXPR,
    SHIFT_EXPR,
    ADD_EXPR,
    MUL_EXPR,
    POW_EXPR,
    EQUAL_EXPR,
    COMPARE_EXPR,
    REGEX_EXPR
  );

  TokenSet PARENTHESISED_LIKE_EXPRESSIONS = TokenSet.orSet(
    TokenSet.create(PARENTHESISED_EXPR),
    PerlTokenSets.VARIABLE_DECLARATIONS
  );

  TokenSet OPERATORS_ASSIGNMENT = TokenSet.orSet(
    BITWISE_ASSIGN_OPERATORS_TOKENSET,
    TokenSet.create(
      OPERATOR_ASSIGN,
      OPERATOR_POW_ASSIGN,
      OPERATOR_PLUS_ASSIGN,
      OPERATOR_MINUS_ASSIGN,
      OPERATOR_MUL_ASSIGN,
      OPERATOR_DIV_ASSIGN,
      OPERATOR_MOD_ASSIGN,
      OPERATOR_CONCAT_ASSIGN,
      OPERATOR_X_ASSIGN,
      OPERATOR_SHIFT_LEFT_ASSIGN,
      OPERATOR_SHIFT_RIGHT_ASSIGN,
      OPERATOR_AND_ASSIGN,
      OPERATOR_OR_ASSIGN,
      OPERATOR_OR_DEFINED_ASSIGN
    ));


  TokenSet BINARY_OPERATORS = TokenSet.orSet(
    OPERATORS_ADDITIVE,
    BITWISE_BINARY_OPERATORS_TOKENSET,
    OPERATORS_EQUALITY,
    OPERATORS_RELATIONAL,
    OPERATORS_LOGICAL,
    OPERATORS_RANGE,
    OPERATORS_MULTIPLICATIVE,
    OPERATORS_ALPHABETICAL,
    OPERATORS_SHIFT
  );

  TokenSet RESERVED_VARIABLE_DECLARATION = TokenSet.create(
    RESERVED_MY,
    RESERVED_OUR,
    RESERVED_LOCAL,
    RESERVED_STATE
  );

  TokenSet LABEL_KEYWORDS = TokenSet.orSet(PerlTokenSets.LOOP_CONTROL_KEYWORDS, TokenSet.create(RESERVED_GOTO));

  TokenSet VARIABLE_LEFT_BRACES = TokenSet.create(
    LEFT_BRACE_SCALAR,
    LEFT_BRACE_ARRAY,
    LEFT_BRACE_HASH,
    LEFT_BRACE_GLOB,
    LEFT_BRACE_CODE
  );

  TokenSet BLOCK_LEFT_BRACES = TokenSet.orSet(
    VARIABLE_LEFT_BRACES,
    TokenSet.create(LEFT_BRACE)
  );

  TokenSet VARIABLE_RIGHT_BRACES = TokenSet.create(
    RIGHT_BRACE_SCALAR,
    RIGHT_BRACE_ARRAY,
    RIGHT_BRACE_HASH,
    RIGHT_BRACE_GLOB,
    RIGHT_BRACE_CODE
  );

  TokenSet BLOCK_RIGHT_BRACES = TokenSet.orSet(
    VARIABLE_RIGHT_BRACES,
    TokenSet.create(RIGHT_BRACE)
  );

  TokenSet BLOCK_OPENERS = TokenSet.create(
    LEFT_BRACE,
    LEFT_BRACKET,
    LEFT_PAREN
  );

  TokenSet BLOCK_CLOSERS = TokenSet.create(
    RIGHT_BRACE,
    RIGHT_BRACKET,
    RIGHT_PAREN,

    SEMICOLON
  );

  TokenSet COMMA_LIKE_SEQUENCES = TokenSet.create(SIGNATURE_CONTENT, COMMA_SEQUENCE_EXPR);

  /**
   * Elements that must have LF between them
   */
  TokenSet LF_ELEMENTS = TokenSet.orSet(
    STATEMENTS,
    COMPOUND_STATEMENTS_TOKENSET,
    TokenSet.create(LABEL_DECLARATION));


  // containers which has none indentation
  TokenSet UNINDENTABLE_CONTAINERS = TokenSet.orSet(
    SUB_OR_MODIFIER_DEFINITIONS_TOKENSET,
    COMPOUND_STATEMENTS_TOKENSET,
    TokenSet.create(
      NAMESPACE_DEFINITION,
      NAMESPACE_CONTENT,

      SUB_DECLARATION,

      DEFAULT_COMPOUND,
      CONDITIONAL_BLOCK,
      CONTINUE_BLOCK,
      BLOCK_COMPOUND,

      TRYCATCH_EXPR,
      TRY_EXPR,
      CATCH_EXPR,
      FINALLY_EXPR,
      EXCEPT_EXPR,
      OTHERWISE_EXPR,
      CONTINUATION_EXPR,

      // fixme see #745
      SWITCH_COMPOUND,
      CASE_COMPOUND,

      DO_BLOCK_EXPR,
      EVAL_EXPR,
      SUB_EXPR,
      PerlStubElementTypes.FILE
    ));

  TokenSet UNINDENTABLE_TOKENS = TokenSet.orSet(
    TokenSet.create(
      COMMA_SEQUENCE_EXPR,
      CALL_ARGUMENTS,
      REGEX_QUOTE_CLOSE,
      ATTRIBUTES
    ));

  TokenSet FORMATTING_BLOCK_LIKE_CONTAINERS = TokenSet.orSet(BLOCK_LIKE_CONTAINERS, TokenSet.create(SIGNATURE_CONTENT));

  TokenSet MULTI_PARAM_BLOCK_CONTAINERS = TokenSet.create(
    GREP_EXPR, MAP_EXPR, SORT_EXPR, REPLACEMENT_REGEX
  );
  TokenSet FOR_ELEMENTS_TOKENSET = TokenSet.create(
    FOR_INIT, FOR_CONDITION, FOR_MUTATOR
  );
  /**
   * Tokens that must be suppressed for indentation
   */
  TokenSet ABSOLUTE_UNINDENTABLE_TOKENS = TokenSet.create(
    HEREDOC_END,
    POD,
    FORMAT,
    FORMAT_TERMINATOR,
    TAG_DATA,
    TAG_END
  );

  /**
   * These are minimal blocks we need formatting model for to compute indent, alignment properly.
   */
  TokenSet FORMATTING_SUFFICIENT_BLOCKS = TokenSet.orSet(
    TRANSPARENT_ELEMENT_TYPES,
    HEREDOC_BODIES_TOKENSET,
    COMPOUND_STATEMENTS,
    BLOCK_LIKE_CONTAINERS,
    TokenSet.create(
      ANON_HASH, ANON_ARRAY,
      NAMESPACE_CONTENT, NAMESPACE_DEFINITION,
      SIGNATURE_CONTENT, PARENTHESISED_CALL_ARGUMENTS, PARENTHESISED_EXPR)
  );

  /**
   * These tokens can limit self-sufficient block range. E.g:
   * <pre>
   *
   * if(something){
   *   ...
   * }
   *
   * &lt;selection&gt;say 'hi';&lt;/selection&gt;
   *
   * if( otherthing ){
   *
   * }
   *
   * </pre>
   * We don't need if compounds for proper formatting and therefore don't need to include them to the model.
   */
  TokenSet FORMATTING_RANGE_EDGE_ELEMENTS = TokenSet.orSet(
    COMPOUND_STATEMENTS, TokenSet.create(NAMESPACE_DEFINITION)
  );

  TokenSet CHAINED_ARRAY_OPERATION_TOKENS = TokenSet.create(
    GREP_EXPR,
    MAP_EXPR,
    SORT_EXPR,
    KEYS_EXPR,
    VALUES_EXPR,
    EACH_EXPR,
    SPLICE_EXPR,
    ARRAY_VARIABLE,
    ARRAY_CAST_EXPR,
    SUB_CALL,
    CALL_ARGUMENTS
  );

}
